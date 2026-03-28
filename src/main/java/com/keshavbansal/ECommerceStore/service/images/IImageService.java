package com.keshavbansal.ECommerceStore.service.images;

import com.keshavbansal.ECommerceStore.dto.ImageDto;
import com.keshavbansal.ECommerceStore.model.Images;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IImageService {

    Images getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImage(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);

}
