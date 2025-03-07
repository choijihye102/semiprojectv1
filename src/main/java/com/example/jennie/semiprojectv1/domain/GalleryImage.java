package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GalleryImage {

    private String imgname;
    private Long imgsize;  // byte -> kbyte로 바꿀예정 /1000

}
