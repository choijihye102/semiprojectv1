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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    @DisplayName("/find GET request test")
    public void find() throws Exception {
        //Given
        String cpg = "1"; // 출력할 페이지 지정
        String findtype = "userid";
        String findkey = "abc";

        //When
        mockMvc.perform(get("/board/find")
                        .param("cpg", cpg)
                        .param("findtype", findtype)
                        .param("findkey", findkey))
                         .andExpect(status().isOk())
                    //    .andExpect(view().name("views/board/list"))
                        .andExpect(model().attributeExists("bds"))
                       .andExpect(model().attribute("bds", hasSize(greaterThan(0)))) // 객체 내 요소의 갯수 비교
                      .andExpect(model().attributeExists("cntpg"))
                      .andExpect(model().attribute("cntpg", greaterThan(0)));; // 변수의 값 비교
    }

    @Test
    @DisplayName("/view GET request test")
    public void view() throws Exception {
        //Given
        String bno = "3000";

        //When
        mockMvc.perform(get("/board/view")
                        .param("bno", bno))
                .andExpect(status().isOk())
                 //.andExpect(view().name("board/view"))
                .andExpect(model().attributeExists("bd"));
         //     .andExpect(model().attribute("bd", hasSize(greaterThan(0)))); // 객체 내 요소의 갯수 비교

    }
}
