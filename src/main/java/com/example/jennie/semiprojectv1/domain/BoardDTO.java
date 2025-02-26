package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardDTO {
    private int bno;
    private String title;
    private String userid;
    private LocalDateTime regdate;
    private  String thumbs;
    private  String views;
    //contents 제외함 : List<Board> selectBoard(); 여기 쿼리 가져오는 요소랑 일치시킴

}
