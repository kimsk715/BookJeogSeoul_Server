package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.OpenAPIResult;
import com.app.bookJeog.domain.vo.ChatGPTRequest;
import com.app.bookJeog.domain.vo.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CustomBotService {

    private final RestTemplate template;

    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.model}")
    private String model;

    public List<OpenAPIResult> getDiscussionTopics(List<Long> isbnList) {
        List<OpenAPIResult> results = new ArrayList<>();
        for (Long isbn : isbnList) {
            String prompt = "다음과 같은 ISBN 코드를 가진 『" + isbn + "』 책을 바탕으로 "
                    + "30자 이내의 토론 주제를 추천하고, 주제가 책의 어떤 부분에서 유래했는지 150자 이내로 설명해 주세요.\n";

            ChatGPTRequest request = new ChatGPTRequest(model, prompt);
            ChatGPTResponse response = template.postForObject(apiURL, request, ChatGPTResponse.class);
            String content = response.getChoices().get(0).getMessage().getContent();

            OpenAPIResult topic = ChatResponseParser.parseResponse(isbn, content);
            results.add(topic);
        }
        return results;
    }

}
