package com.team02.best_properta.controller.business;


import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.payload.request.business.ImageRequest;
import com.team02.best_properta.payload.response.business.ImageResponse;
import com.team02.best_properta.service.business.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    //I01 **************************************************************************************//
    @GetMapping("/{imageId}") // http://localhost:8080/images/5 + GET
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long imageId) {
        Image image = imageService.getImageById(imageId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.parseMediaType(image.getType()));
        headers.setContentLength(image.getData().length);
        headers.set("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"");

        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }

    //I02 **************************************************************************************//
    @PostMapping("/{advertId}") // http://localhost:8080/images/5 + POST
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<Long>> uploadImages(@PathVariable Long advertId, @RequestParam("images") List<MultipartFile> files) {
        List<Long> imageIds = imageService.saveImages(advertId, files);
        return new ResponseEntity<>(imageIds, HttpStatus.OK);
    }

    //I03 **************************************************************************************//
    @DeleteMapping("/{imageIds}") //http://localhost:8080/images/5 + DELETE
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<Void> deleteImages(@PathVariable List<Long> imageIds) {
        imageService.deleteImages(imageIds);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    //I04 **************************************************************************************//
    @PutMapping("/{imageId}") //http://localhost:8080/images/5 + PUT
    @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<ImageResponse> setFeatured(@PathVariable Long imageId,
                                                     @RequestBody ImageRequest imageRequest) {
        ImageResponse updatedImage = imageService.setFeatured(imageId, imageRequest);
        return ResponseEntity.ok(updatedImage);
    }






}