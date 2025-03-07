package com.example.jennie.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GalleryImageDTO {

    private GalleryViewDTO gal; // 갤러리 게시글
    private List<?> gi; // 게시글에 포함된 이미지 (5개..)

    public GalleryImageDTO(GalleryViewDTO gal, List<?> gi) {
        this.gal = gal;
        this.gi = gi;
    }


}
