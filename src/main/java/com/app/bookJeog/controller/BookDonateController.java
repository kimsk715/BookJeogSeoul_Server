package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.BookDonateInfoDTO;
import com.app.bookJeog.service.BookDonateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class BookDonateController implements MemberControllerDocs {

}
