package com.example.jennie.semiprojectv1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data // setter, getter, toString 자동 생성
public class MemberDTO {

    private String userid;
    private String passwd;
    private String name;
    private String email;

}
