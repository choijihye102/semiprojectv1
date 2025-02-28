package com.example.jennie.semiprojectv1.controller;

import com.example.jennie.semiprojectv1.domain.Member;
import com.example.jennie.semiprojectv1.domain.MemberDTO;
import com.example.jennie.semiprojectv1.repository.MemberRepository;
import com.example.jennie.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping ("/join")
    public String join() {

        return "views/member/join";

    }

    // ResponseEntity는 스프링에서 HTTP와 관련된 기능을 구현할때 사용
    // 상태코드, HTTP헤더, HTTP본문등을 명시적으로 설정 가능.
    @PostMapping ("/join")
    public ResponseEntity<?> joinok(MemberDTO member) {
        // 회원가입 처리시 기타오류 발생에 대한 응답코드
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보 :  {}", member);

        try{
            // 정상 처리시 상태코드 200으로 응답
            memberService.newMember(member);
            response = ResponseEntity.ok().build();
        } catch(IllegalStateException e){
            //  비정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못으로 인한 오류 일때
            //  중복 아이디나 중복 이메일 사용시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        } catch ( Exception e){
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못으로 인한 오류 일때
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        }
        return response;
    }

    @GetMapping("/login")
    public String login() {

        return "views/member/login";
    }

    @PostMapping ("/login")
    public ResponseEntity<?>  loginok(MemberDTO member , HttpSession session ) {
        // 로그인 처리시 기타오류 발생에 대한 응답코드
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("로그인된 회원 정보 :  {}", member);

        try{
            // 정상 처리시 상태코드 200으로 응답
            Member loginUser = memberService.loginMember(member);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(600); // 세션 유지 : 10분

            response = ResponseEntity.ok().build();
        } catch(IllegalStateException e){
            //  비정상 처리시 상태코드 400으로 응답 - 클라이언트 잘못으로 인한 오류 일때
            //  아이디나 비밀번호 잘못 입력시.
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        } catch ( Exception e){
            // 비정상 처리시 상태코드 500으로 응답 - 서버 잘못으로 인한 오류 일때
            e.printStackTrace(); // 틀린이유 내부적으로 보이기 위해
        }
        return response;

    }

    @GetMapping("/myinfo")
    public String myinfo(HttpSession session) {
        String returnUrl = "views/member/login";

        // 세션변수가 생성되어 있다면 myinfo로 이동가능
        if(session.getAttribute("loginUser") != null) {
            returnUrl = "views/member/myinfo";
        }
        return  returnUrl;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 제거.

        return "redirect:/";
    }
}
