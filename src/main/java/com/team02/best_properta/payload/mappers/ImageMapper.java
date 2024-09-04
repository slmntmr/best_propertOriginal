package com.team02.best_properta.payload.mappers;


import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.payload.request.business.ImageRequest;
import com.team02.best_properta.payload.response.business.ImageResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageResponse mapImageToImageResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .name(image.getName())
                .type(image.getType())
                .featured(image.getFeatured())
                .advertId(image.getAdvert().getId())
                .build();
    }

    public Image mapImageRequestToImage(ImageRequest imageRequest, Image existingImage) {
        // Eğer request'ten gelen değerler varsa, güncelleme yapıyoruz
        if (imageRequest.getName() != null) {
            existingImage.setName(imageRequest.getName());
        }
        if (imageRequest.getType() != null) {
            existingImage.setType(imageRequest.getType());
        }
        if (imageRequest.getFeatured() != null) {
            existingImage.setFeatured(imageRequest.getFeatured());
        }

        // Diğer alanlar aynı kalır
        return existingImage;
    }
}