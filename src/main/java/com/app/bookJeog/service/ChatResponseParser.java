package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.OpenAPIResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatResponseParser {
    public static OpenAPIResult parseResponse(Long isbn, String content) {
        String bookTitle = null;
        String topic = null;
        String description = null;

        // 책 제목 추출: 『제목』 형태
        Matcher titleMatcher = Pattern.compile("『(.*?)』").matcher(content);
        if (titleMatcher.find()) {
            bookTitle = titleMatcher.group(1).trim();
        }

        // 토론 주제 추출
        Matcher topicMatcher = Pattern.compile("토론 주제[:\\s]*[\"“]?(.*?)[\"”]?\\n").matcher(content);
        if (topicMatcher.find()) {
            topic = topicMatcher.group(1).trim();
        } else {
            // '**토론 주제 추천:**' 형태 대응
            topicMatcher = Pattern.compile("\\*\\*토론 주제.*?\\*\\*[:\\s]*[\"“]?(.*?)[\"”]?\\n").matcher(content);
            if (topicMatcher.find()) {
                topic = topicMatcher.group(1).trim();
            }
        }

        // 설명 추출 (주제 이후 텍스트 전체)
        if (topic != null) {
            int idx = content.indexOf(topic);
            if (idx != -1) {
                description = content.substring(idx + topic.length())
                        .replaceAll("^[\\s:\\-\\n\\*]+", "")
                        .trim();
            }
        }

        return new OpenAPIResult(isbn, bookTitle, topic, description);
    }
}