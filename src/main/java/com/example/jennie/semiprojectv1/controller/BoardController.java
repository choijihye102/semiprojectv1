package com.example.jennie.semiprojectv1.controller;

import com.example.jennie.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg) {
        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨

        log.info("board/list 호출 !! ");

        m.addAttribute("bds", boardService.readBoard(cpg));
        m.addAttribute("cpg", cpg);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
        m.addAttribute("stblk", ((cpg - 1) / 10) * 10+1);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..


        return "views/board/list";
    }

}
