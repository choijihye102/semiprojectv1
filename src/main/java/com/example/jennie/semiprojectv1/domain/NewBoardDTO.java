package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBoardDTO {
    private String title;
    private String userid;
    private String contents;
}