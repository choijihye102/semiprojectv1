package com.example.jennie.semiprojectv1.member;

import com.example.jennie.semiprojectv1.controller.MemberController;
import com.example.jennie.semiprojectv1.domain.Member;
import com.example.jennie.semiprojectv1.domain.MemberDTO;
import com.example.jennie.semiprojectv1.repository.MemberRepository;
import com.example.jennie.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.expression.Mvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)  // 생성자 주입시 필요
public class MemberControllerTest {

    private final MockMvc  mockMvc;
    private final MemberRepository memberMapper;

    @Test
    @DisplayName("/join Post request test")
    public void joinok() throws Exception {
        //Given
        String userid = "abc1232";
        String password = "987xyz";
        String name = "abc1232";
        String email = "abc1231@gamil,com";

        //When
        mockMvc.perform(post( "/member/join")
                            .param( "userid","abc1232")
                            .param("passwd","987xyz" )
                            .param("name","abc1231" )
                            .param("email","abc1231@gamil,com" ))
                            .andExpect(status().is3xxRedirection())
                            .andExpect(redirectedUrl("/member/login"));

        //Then --> 트랜젝션 필요
//            Member member =memberMapper.findByUserid("userid");
//            assertThat(member).isNull();
//            assertThat(member.getMno()).isNull();
//            assertThat(member.getRegdate()).isNull();
//            assertThat(member.getName()).isEqualTo("abc1231");
//            assertThat(member.getEmail()).isEqualTo("abc1231@gamil,com");

    }
}
