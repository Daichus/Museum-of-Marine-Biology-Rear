package com.example.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
  @Value("${openai.api.key}")
  private String apiKey;

  @Value("${openai.api.url}")
  private String apiUrl;

  @Value("${openai.model.id:gpt-4}")
  private String modelId;

  private final ResourceLoader resourceLoader;
  private String knowledgeBase;

  public ChatController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
    initializeKnowledgeBase();
  }

  private void initializeKnowledgeBase() {
    try {
      Resource resource = resourceLoader.getResource("classpath:knowledge_base.csv");
      StringBuilder sb = new StringBuilder();
      sb.append("以下是海生館的常見問題與答案：\n\n");

      try (BufferedReader br = new BufferedReader(
              new InputStreamReader(resource.getInputStream()))) {
        String line;
        while ((line = br.readLine()) != null) {
          // 使用問題和答案的分隔來分割每行
          if (line.contains("**問題**") || line.trim().isEmpty()) {
            continue; // 跳過標題行和空行
          }

          if (line.startsWith("**")) {
            String question = line.replace("**", "").trim();
            // 讀取下一行作為答案
            String answer = br.readLine();
            if (answer != null) {
              sb.append("Q: ").append(question).append("\n");
              sb.append("A: ").append(answer).append("\n\n");
            }
          }
        }
      }

      sb.append("\n作為海生館的AI客服助理，請遵循以下準則：\n");
      sb.append("1. 優先使用上述知識庫的資訊來回答訪客問題\n");
      sb.append("2. 若問題完全符合知識庫內容，請直接使用知識庫的答案\n");
      sb.append("3. 若問題只是部分相關，請根據知識庫內容進行適當的延伸回答\n");
      sb.append("4. 若問題不在知識庫中，請基於海生館的一般營運邏輯來回答\n");
      sb.append("5. 始終保持親切、專業的回答態度\n");
      sb.append("6. 若遇到不確定的資訊，請建議訪客直接聯繫海生館確認\n");

      knowledgeBase = sb.toString();
      System.out.println("知識庫載入成功");
    } catch (Exception e) {
      System.err.println("無法載入知識庫: " + e.getMessage());
      e.printStackTrace();
      knowledgeBase = "我是海生館的客服助理，很高興為您服務。";
    }
  }

  @PostMapping("/api/chat")
  public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
    String userMessage = request.get("message");
    try {
      if (userMessage == null || userMessage.trim().isEmpty()) {
        throw new IllegalArgumentException("消息不能為空");
      }
      String gptResponse = callChatGPT(userMessage);
      Map<String, String> response = new HashMap<>();
      response.put("response", gptResponse);
      return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(errorResponse);
    } catch (Exception e) {
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", "系統發生錯誤: " + e.getMessage());
      return ResponseEntity.status(500).body(errorResponse);
    }
  }

  private String callChatGPT(String message) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("model", modelId);

    List<Map<String, String>> messages = new ArrayList<>();

    Map<String, String> systemMessage = new HashMap<>();
    systemMessage.put("role", "system");
    systemMessage.put("content", knowledgeBase);

    Map<String, String> userMessage = new HashMap<>();
    userMessage.put("role", "user");
    userMessage.put("content", message);

    messages.add(systemMessage);
    messages.add(userMessage);
    requestBody.put("messages", messages);

    String jsonRequest = mapper.writeValueAsString(requestBody);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
            .build();

    HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      JsonNode errorRoot = mapper.readTree(response.body());
      String errorMessage = errorRoot.has("error") ?
              errorRoot.get("error").get("message").asText() :
              "API request failed with status: " + response.statusCode();
      throw new Exception(errorMessage);
    }

    JsonNode root = mapper.readTree(response.body());
    return root.get("choices").get(0).get("message").get("content").asText();
  }
}
