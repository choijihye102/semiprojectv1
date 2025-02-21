package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data // setter, getter, toString 자동 생성
public class Member {

    private int mno;
    private String userid;
    private String passwd;
    private String repasswd;  // input으로 받은거 처리해야해서 db와 완전 같지 않음
    private String name;
    private String email;
    private LocalDateTime regdate;
}
