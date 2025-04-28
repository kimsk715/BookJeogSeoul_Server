package com.app.bookJeog.controller.admin;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 임시 컨트롤러
@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminWebController {

    @GetMapping("admin")
    public String goToAdminMain(HttpSession session) {


        return "admin/admin_page";
    }
}
