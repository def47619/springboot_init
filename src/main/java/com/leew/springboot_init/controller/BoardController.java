package com.leew.springboot_init.controller;

import com.leew.springboot_init.dto.BoardDTO;
import com.leew.springboot_init.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    // Service 클래스 호출해야 한다.
    private final BoardService boardService; // 생성자 주입으로 생성

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println(boardDTO); // boardDTO toString 설정해 놓음
        boardService.save(boardDTO);
        return "index";
    }

}
