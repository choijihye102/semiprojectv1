package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.Gallery;
import com.example.jennie.semiprojectv1.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{

    private final GalleryRepository galleryRepository;

    @Override
    public List<Gallery> selectGallery() {

        return galleryRepository.selectGallery();
    }
}
