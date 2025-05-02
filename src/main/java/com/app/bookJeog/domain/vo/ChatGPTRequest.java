package com.app.bookJeog.domain.vo;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("system", "당신은 독서 토론 주제를 추천하는 전문가입니다."));
        this.messages.add(new Message("user", prompt));

    }
}
