package com.example.jennie.semiprojectv1.member;

import com.example.jennie.semiprojectv1.domain.MemberDTO;
import com.example.jennie.semiprojectv1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@MybatisTest
public class MemberMapperTest {

    @Autowired
    private  MemberRepository memberMapper;

    @Test
    @DisplayName("MemberMapper Insert test")
    void insertMemberTest(){

        // Given : 테스트에 사용할 데이터 제공
        MemberDTO dto = MemberDTO.builder()
                .userid("adc1234")
                .passwd("987xyz")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        // when :  데이터로 테스트할 기능 호출
        int result = memberMapper.insertMember(dto);  // 1이 넘어와야 쿼리호출 성공.

        //then : 호출되고 난 후 결과값 확인
        log.info("result: {}", result);
        assertThat(result).isEqualTo(1);


    }

}
