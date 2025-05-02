package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.OpenAPIResult;
import com.app.bookJeog.domain.vo.ChatGPTRequest;
import com.app.bookJeog.domain.vo.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CustomBotService {

    private final RestTemplate template;
    private final BookService bookService;
    private final ChatResponseParser chatResponseParser;


    @Value("${openai.api.url}")
    private String apiURL;

    @Value("${openai.model}")
    private String model;

    public List<OpenAPIResult> getDiscussionTopics(Long isbn) {
        List<OpenAPIResult> results = new ArrayList<>();
            String bookTitle = bookService.getBookByIsbn(isbn).get(0).getTitle();
            // 책 제목 추출
            String prompt = "다음과 같은 ISBN" + isbn + "코드를 가진 『" + bookTitle + "』 책을 바탕으로 "
                    + "30자 이내의 토론 주제를 3개 추천하고, 각 주제가 책의 어떤 부분에서 유래했는지 150자 이내로 설명해 주세요.\n"
                    + "출력 패턴은( 번호. \"토론 주제\" - 설명 ) 로 해줘 ";

            ChatGPTRequest request = new ChatGPTRequest(model, prompt);
            ChatGPTResponse response = template.postForObject(apiURL, request, ChatGPTResponse.class);
            String content = response.getChoices().get(0).getMessage().getContent();
            log.info(content);


        return chatResponseParser.parseResponse(isbn, content);
    }

}
