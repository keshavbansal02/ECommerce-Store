package com.keshavbansal.ECommerceStore.service.images;

import com.keshavbansal.ECommerceStore.dto.ImageDto;
import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Images;
import com.keshavbansal.ECommerceStore.model.Product;
import com.keshavbansal.ECommerceStore.repository.ImagesRepository;
import com.keshavbansal.ECommerceStore.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final IProductService productService;

    private final ImagesRepository imageRepository;

    @Override
    public Images getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Image not found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        Images image = imageRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Image not found with id: " + id));

        imageRepository.delete(image);

    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> imageDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Images image = new Images();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                Images savedImage = imageRepository.save(image);
                String downloadUrl = "/api/v1/images/image/download/" + image.getId();
                savedImage.setDownloadUrl(downloadUrl);
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setFileName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                imageDtos.add(imageDto);

            } catch (Exception e) {
                throw new RuntimeException("Failed to save image: " + e.getMessage());
            }

        }
        return imageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

        Images image =  getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update image: " + e.getMessage());
        }

    }
}
