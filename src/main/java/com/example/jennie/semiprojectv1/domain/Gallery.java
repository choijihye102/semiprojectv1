package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Gallery {
    private int gno;
    private String title;
    private String userid;
    private  String thumbs;
    private  String views;
    private  String simgname;
    private LocalDateTime regdate;


 //   private  String contents;


}
