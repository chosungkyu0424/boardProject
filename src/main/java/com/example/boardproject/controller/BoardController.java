package com.example.boardproject.controller;

import com.example.boardproject.Sevice.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    /**
     * 글작성 페이지
     */
    @GetMapping("/board/save")
    public String save() {
        return "layout/board/board-save";
    }

    /**
     * 글상세 페이지
     */
    //{id}주소로 파라미터를 전달받을 수 있음
    //http://localhost:8080/board/1
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        boardService.updateCount(id);
        return "layout/board/board-detail";
    }

    /**
     * 글수정 페이지
     */
    @GetMapping("/board/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.detail(id));
        return "layout/board/board-update";
    }
}
