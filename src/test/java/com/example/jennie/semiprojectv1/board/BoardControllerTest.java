package com.example.jennie.semiprojectv1.board;

import com.example.jennie.semiprojectv1.repository.BoardRepository;
import com.example.jennie.semiprojectv1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)  // 생성자 주입시 필요
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //H2 데이터베이스 사용중지
public class BoardControllerTest {

    private final MockMvc  mockMvc;
    private final BoardRepository boardRepository;

    @Test
    @DisplayName("/list GET request test")
    public void list() throws Exception {
        //Given
        String cpg =  "1"; // 출력할 페이지 지정. 파라미터 넘어올댄 문자임.
        //When
        mockMvc.perform(get( "/board/list").param("cpg",cpg))
                            .andExpect(status().isOk())
                            .andDo(print());


        //Then --> 트랜젝션 필요
//            Member member =memberMapper.findByUserid("userid");
//            assertThat(member).isNull();
//            assertThat(member.getMno()).isNull();
//            assertThat(member.getRegdate()).isNull();
//            assertThat(member.getName()).isEqualTo("abc1231");
//            assertThat(member.getEmail()).isEqualTo("abc1231@gamil,com");

    }
}
