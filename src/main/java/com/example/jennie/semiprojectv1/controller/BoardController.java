package com.example.jennie.semiprojectv1.controller;

import com.example.jennie.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @Value(  "${board.page-size}") private  int pageSize;

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg, HttpServletResponse response) {
        //클라이언트 캐시 제어 : - view +1 작업 연장선
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires","0");
        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
        log.info("board/list 호출 !! ");

        m.addAttribute("bds", boardService.readBoard(cpg, pageSize));
        m.addAttribute("cpg", cpg);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
        m.addAttribute("stblk", ((cpg - 1) / 10) * 10+1);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
        m.addAttribute("cntpg", boardService.countBoard(pageSize));


        return "views/board/list";
    }

    @GetMapping("/find")
    public String find(Model m, String findtype, String findkey,
                       @RequestParam(defaultValue = "1") int cpg) {

        m.addAttribute("bds", boardService.findBoard(cpg, findtype, findkey));
        m.addAttribute("cpg", cpg);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
        m.addAttribute("stblk", ((cpg - 1) / 10) * 10+1);
        m.addAttribute("cntpg", boardService.countfindBoard(findtype,findkey));  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..

        return "views/board/list";
    }

    @GetMapping("/view")
    public String find(Model m, int bno) {

        boardService.readOneView(bno);
        m.addAttribute("bd", boardService.readOndBoard(bno));

        return "views/board/view";
    }

}
