package com.example.jennie.semiprojectv1.board;

import com.example.jennie.semiprojectv1.domain.Board;
import com.example.jennie.semiprojectv1.domain.BoardDTO;
import com.example.jennie.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional // insert 후에 데이터 돌려놔야해서 롤백 써야함
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)  // 생성자 주입시 필요
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //H2 데이터베이스 사용중지
public class BoardServiceTest {

    private final BoardService boardService;
    @Value(  "${board.page-size}") private  int pageSize;

    @Test
    @DisplayName("BoardService readall test")
    public void readAllTest(){
        // Given

        int cpg = 1;  //  현재  페이지가 1일때 게시글을 읽어옴
        // when
        List<BoardDTO> results =  boardService.readBoard(cpg, pageSize);

        // then
        assertNotNull(results);
    }

    @Test
    @DisplayName("BoardService find test")
    public void findlTest(){
        // Given
        int cpg = 1;
        String findtype = "title";
        String findkey = "흉기";


        // when
        List<BoardDTO> results =  boardService.findBoard(cpg, findtype, findkey);

        // then
        assertNotNull(results); // null 여부 확인  -> 리스트일 경우 의미없는 검사 !
        assertThat(results).isNotEmpty(); // 비어있는지 여부 확인
        assertThat(results.size()).isGreaterThan(0); // 결과 갯수 확인
    }

    @Test
    @DisplayName("BoardService countfind test")
    public void countfindTest(){
        // Given
            String findtype = "title";
            String findkey = "흉기";

        // when
        int results =  boardService.countfindBoard(findtype, findkey);

        // then
        assertThat(results).isGreaterThan(0); // 결과 갯수 확인
    }

    @Test
    @DisplayName("BoardService readOne test")
    public void readOndTest(){
        // Given
        String bno = "3000";

        // when
        Board result =  boardService.readOndBoard(bno);

        // then
        assertThat(result).isNotNull(); // 결과 갯수 확인
        assertThat(result.getUserid()).isNotNull(); // 결과 갯수 확인
    }

}
