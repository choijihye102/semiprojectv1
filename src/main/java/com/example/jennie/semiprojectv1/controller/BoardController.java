package com.example.jennie.semiprojectv1.controller;

import com.example.jennie.semiprojectv1.domain.NewBoardDTO;
import com.example.jennie.semiprojectv1.domain.NewReplyDTO;
import com.example.jennie.semiprojectv1.service.BoardService;
import com.example.jennie.semiprojectv1.service.GoogleRecaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final GoogleRecaptchaService googleRecaptchaService;
    @Value("${board.page-size}")
    private int pageSize;

    @GetMapping("/list")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg, HttpServletResponse response) {
        //클라이언트 캐시 제어 : - view +1 작업 연장선
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");
        //cpg 매개변수가 전달되지 않을 경우 기본값인 1이 전달됨
        log.info("board/list 호출 !! ");

//new
        m.addAttribute("bdsdto", boardService.readBoard(cpg));

 // old
//        m.addAttribute("bds", boardService.readBoard(cpg, pageSize));
//        m.addAttribute("cpg", cpg);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
//        m.addAttribute("stblk", ((cpg - 1) / 10) * 10 + 1);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
//        m.addAttribute("cntpg", boardService.countBoard(pageSize));


        return "views/board/list";
    }

    @GetMapping("/find")
    public String find(Model m, String findtype, String findkey,
                       @RequestParam(defaultValue = "1") int cpg) {

        m.addAttribute("bds", boardService.findBoard(cpg, findtype, findkey));
        m.addAttribute("cpg", cpg);  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..
        m.addAttribute("stblk", ((cpg - 1) / 10) * 10 + 1);
        m.addAttribute("cntpg", boardService.countfindBoard(findtype, findkey));  //매개변수로 받은 cpg를 '이전' 태그에 cpg-1 연산을 위해 다시 모델에 담아 보냄..

        return "views/board/list";
    }

    @GetMapping("/view")
    public String find(Model m, int bno) {

 // new
        m.addAttribute("bdrps", boardService.readOndBoardReply(bno));

// old -> 아래코드 서비스impl로 옮겨감.
//        boardService.readOneView(bno);
//        m.addAttribute("bd", boardService.readOndBoard(bno));
//        m.addAttribute("rps", boardService.readReply(bno));

        return "views/board/view";
    }

    @GetMapping("/write")
    public String write(Model m, HttpSession session) {
        String returnPage = "redirect:/member/login";

        if(session.getAttribute("loginUser") != null) {
            // 시스템 환경변수에 저장된 사이트키 불러옴.
            m.addAttribute("sitekey",System.getenv("recaptcha.sitekey"));
            returnPage = "views/board/write";
        }


        return returnPage;
    }

    @PostMapping("/write")
    public ResponseEntity<?> writeok(NewBoardDTO newBoardDTO,
                                     @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("submit된 게시글 정보 : {}", newBoardDTO);
        log.info("submit된 recaptcha 응답코드 : {}", gRecaptchaResponse);

        try {
            if (!googleRecaptchaService.verifyRecaptcha(gRecaptchaResponse)) {
                throw new IllegalStateException("자동가입방지 코드 오류!!");
            }

            if (boardService.newBoard(newBoardDTO)) {
                response = ResponseEntity.ok().build();
            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/reply")
    public String replyok(NewReplyDTO newReplyDTO){
        String returnPage =  "redirect:/board/list?bno=" + newReplyDTO.getPno();

        if (boardService.newReply(newReplyDTO)){
            returnPage = "redirect:/board/error?type=1";
        }

        return returnPage;
    }

    @PostMapping("/cmmt")
    public String cmmtok(NewReplyDTO newReplyDTO){
        String returnPage =  "redirect:/board/list?bno=" + newReplyDTO.getPno();

        if (!boardService.newComment(newReplyDTO)){
            returnPage = "redirect:/board/error?type=1";
        }

        return returnPage;
    }
}