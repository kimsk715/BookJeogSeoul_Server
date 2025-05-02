package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.OpenAPIResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChatResponseParser {

    private final BookService bookService;


    public List<OpenAPIResult> parseResponse(Long isbn, String content) {
        List<OpenAPIResult> results = new ArrayList<>();

        String bookTitle = bookService.getBookByIsbn(isbn).get(0).getTitle(); // 책 제목이 포함되지 않으면 ISBN 사용

        // 정규식: 번호. "주제" - 설명
        Pattern pattern = Pattern.compile("(\\d+)\\.\\s*\"(.*?)\"\\s*-\\s*(.*?)(?=\\n\\d+\\.|\\z)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            Long index = Long.parseLong(matcher.group(1).trim());
            String topic = matcher.group(2).trim();
            String description = matcher.group(3).trim();

            OpenAPIResult result = OpenAPIResult.builder()
                    .index(index)
                    .isbn(isbn)
                    .bookTitle(bookTitle)
                    .topic(topic)
                    .description(description)
                    .build();

            results.add(result);
        }

        return results;
    }
}