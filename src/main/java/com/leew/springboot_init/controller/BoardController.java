package com.leew.springboot_init.controller;

import com.leew.springboot_init.dto.BoardDTO;
import com.leew.springboot_init.service.BoardService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();

        model.addAttribute("boardList", boardDTOList);
        // list html파일로 넘어간다.
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        /*
        2가지를 고민 해야 함
        1. 해당 게시글의 조회 수를 하나 올리고,
        2. 게시글 데이터를 가져 와서 detail.html에 출력
         */

        // 조회 수를 하나 올리고
        boardService.updateHits(id);

        // 게시글 데이터를 가져 와서, detail.html에 출력
        BoardDTO boardDTO = boardService.findByID(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        /*
        해당 게시글의 정보를 먼저 갖고 오기
         */
        BoardDTO boardDTO = boardService.findByID(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board); // detail 페이지의 내용을 Model에 전달
        return "detail"; // 해당 변경된 내용을 가진 상세 페이지로 다시 이동
        // return "redirect:/board/" + boardDTO.getId(); // 리디렉션을 해도 되지만, 수정을 해도 조회수가 1 늘어나는 작동을 막기 위해
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        // 기능 1 : 삭제한다. (BoardService에 삭제 기능 구현)
        boardService.delete(id);

        // 기능 2 : 완료하면, 리디렉션 처리 - 해당 상세 페이지를 더 이상 볼 수 없으니, 목록 페이지로 이동
        return "redirect:/board/";
    }
}
