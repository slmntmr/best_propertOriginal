package com.team02.best_properta.service.business;



import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.ImageMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.request.business.ImageRequest;
import com.team02.best_properta.payload.response.business.ImageResponse;
import com.team02.best_properta.repository.business.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {


    private final ImageRepository imageRepository;
    private final AdvertsService advertService;
    private final ImageMapper imageMapper;

    public Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND_MESSAGE));
    }

    // **************************************************************************************//


    public List<Long> saveImages(Long advertId, List<MultipartFile> files) {
        List<Long> imageIds = new ArrayList<>();
        Advert advert = advertService.getAdvertById(advertId); // AdvertService kullanılarak Advert alınır

        for (MultipartFile file : files) {
            try {
                Image image = Image.builder()
                        .data(file.getBytes())
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .featured(false) // Varsayılan olarak false
                        .advert(advert)
                        .build();

                Image savedImage = imageRepository.save(image);
                imageIds.add(savedImage.getId());
            } catch (IOException e) {
                throw new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_SAVED_MESSAGE);
            }
        }
        return imageIds;
    }

    // **************************************************************************************//

    public void deleteImages(List<Long> imageIds) {
        List<Image> images = imageRepository.findAllById(imageIds);
        if (images.size() != imageIds.size()) {
            throw new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND_MESSAGE);
        }
        imageRepository.deleteAll(images);
    }

    // **************************************************************************************//

    @Transactional
    public ImageResponse setFeatured(Long imageId, ImageRequest imageRequest) {
        // Veritabanından mevcut Image entity'sini alıyoruz
        Image existingImage = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.IMAGE_NOT_FOUND_MESSAGE));

        // Advert'in sadece bir featured image'e sahip olabilmesi için kontrol ediyoruz
        if (imageRequest.getFeatured() != null && imageRequest.getFeatured()) {
            imageRepository.updateAllFeaturedByAdvertId(existingImage.getAdvert().getId());
        }

        // ImageRequest'ten gelen verilerle mevcut Image entity'sini güncelliyoruz
        Image updatedImage = imageMapper.mapImageRequestToImage(imageRequest, existingImage);

        // Güncellenmiş Image entity'sini kaydediyoruz
        Image savedImage = imageRepository.save(updatedImage);

        // Kaydedilen entity'yi DTO'ya çevirip geri dönüyoruz
        return imageMapper.mapImageToImageResponse(savedImage);
    }

}
