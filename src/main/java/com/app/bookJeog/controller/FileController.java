package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class FileController {

    @ResponseBody
    @GetMapping("display")
    public byte[] display(@RequestParam String path) throws IOException {
        byte[] file = null;
        try {
            file = FileCopyUtils.copyToByteArray(new File("/upload/" + path));
        }catch (NoSuchFileException e){
            throw new RuntimeException();
        }
        // 파일을 바이트 배열로 변환함
        return file;
        // th:src="@{'/enterprise/display?path=' + ${topList.filePath} + '/' + ${topList.fileName}}" 와 같이 출력,
    }
}
