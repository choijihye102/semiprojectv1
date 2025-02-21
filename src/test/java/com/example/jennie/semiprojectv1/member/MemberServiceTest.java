package com.example.jennie.semiprojectv1.member;

import com.example.jennie.semiprojectv1.domain.MemberDTO;
import com.example.jennie.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional // insert 후에 데이터 돌려놔야해서 롤백 써야함
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)  // 생성자 주입시 필요
public class MemberServiceTest {

    private final MemberService memberService;

    @Test
    @DisplayName("MemberService newMember test")
    public void insertMemberTest(){
        // Given
        MemberDTO dto = MemberDTO.builder()
                .userid("adc1234")
                .passwd("987xyz")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        // when
        boolean result = memberService.newMember(dto);

        // then
        assertTrue(result);
    }

}
