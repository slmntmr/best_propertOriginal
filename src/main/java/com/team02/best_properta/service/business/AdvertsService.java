package com.team02.best_properta.service.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.exception.AdvertNotFoundException;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.AdvertMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.request.business.*;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import com.team02.best_properta.repository.business.AdvertsRepository;
import com.team02.best_properta.service.helper.MethodHelper;
import com.team02.best_properta.service.helper.PageableHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
/*@RequiredArgsConstructor*/
public class AdvertsService {


   /* private final AdvertMapper advertMapper;
    private final AdvertsRepository advertsRepository;
    private final PageableHelper pageableHelper;
    private final MethodHelper methodHelper;
    private final CategoryPropertyKeyService categoryPropertyKeyService;
    private final CategoryPropertyValueService categoryPropertyValueService;
    private final TourRequestsService tourRequestService;
    private final ImageService imageService;
    private final FavoritesService favoriteService;
    private final LogsService logService;*/


    private AdvertMapper advertMapper;
    private AdvertsRepository advertsRepository;
    private PageableHelper pageableHelper;
    private MethodHelper methodHelper;
    private CategoryPropertyKeyService categoryPropertyKeyService;
    private CategoryPropertyValueService categoryPropertyValueService;
    private TourRequestsService tourRequestService;
    private ImageService imageService;
    private FavoritesService favoriteService;
    private LogsService logService;

    // Setter methods for dependency injection
    public void setAdvertMapper(AdvertMapper advertMapper) {
        this.advertMapper = advertMapper;
    }

    public void setAdvertsRepository(AdvertsRepository advertsRepository) {
        this.advertsRepository = advertsRepository;
    }

    public void setPageableHelper(PageableHelper pageableHelper) {
        this.pageableHelper = pageableHelper;
    }

    public void setMethodHelper(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    public void setCategoryPropertyKeyService(CategoryPropertyKeyService categoryPropertyKeyService) {
        this.categoryPropertyKeyService = categoryPropertyKeyService;
    }

    public void setCategoryPropertyValueService(CategoryPropertyValueService categoryPropertyValueService) {
        this.categoryPropertyValueService = categoryPropertyValueService;
    }

    public void setTourRequestService(TourRequestsService tourRequestService) {
        this.tourRequestService = tourRequestService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setFavoriteService(FavoritesService favoriteService) {
        this.favoriteService = favoriteService;
    }

    public void setLogService(LogsService logService) {
        this.logService = logService;
    }


    public boolean userHasAdverts(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return advertsRepository.existsByUserId(userId);
    }


    //************************************************************





    public Advert getAdvertById(Long advertId) {
        return advertsRepository.findById(advertId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ADVERT_NOT_FOUND_MESSAGE));
    }


    //************************************************************






    public List<Advert> findAdvertsByCriteria(LocalDate date1, LocalDate date2, String category, String advertType, String status) {
        return advertsRepository.findAdvertsByCriteria(date1.atStartOfDay(), date2.atStartOfDay(), category, advertType, status);
    }

    public int countActivatedAdverts() {
        return advertsRepository.countActivatedAdverts();
    }

    public List<AdvertResponse> getAdverts(LocalDateTime date1, LocalDateTime date2, String category, String type, String status) {
        List<Advert> adverts = advertsRepository.findAdvertsByCriteria(date1, date2, category, type, status);
        return adverts.stream()
                .map(advertMapper::toAdvertResponse) // Lambda ifadesi kullanarak dönüşümü yapın
                .collect(Collectors.toList());
    }
    public List<Advert> getMostPopularProperties(int amount) {

        return advertsRepository.findMostPopularProperties(amount);
    }

    //************************************************************

    public Page<AdvertResponse> getAdverts(String q, Long categoryId, Long advertTypeId, BigDecimal priceStart, BigDecimal priceEnd, Integer status, int page, int size, String sort, String type) {
        //Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortField);
        //PageRequest pageRequest = PageRequest.of(page, size, sort);
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        // HQL sorgusunu kullanarak Advert listesi alınır
        Page<Advert> adverts = advertsRepository.findAdverts(q, categoryId, advertTypeId, priceStart, priceEnd, status, pageable);

        // Advert listesi AdvertResponse listesine dönüştürülür
        return adverts.map(advertMapper::mapToAdvertResponse);

    }
    public List<CityDTO> getAdvertCountsByCity() {
        List<Object[]> results = advertsRepository.countAdvertsByCity();
        return results.stream()
                .map(obj -> new CityDTO((String) obj[0], (Long) obj[1]))
                .collect(Collectors.toList());
    }
    public List<CategoryDTO> getAdvertsGroupedByCategory() {
        // Veritabanından gelen sonuç
        List<Object[]> results = advertsRepository.findAdvertsGroupedByCategory();

        // Dönüştürme işlemi
        return results.stream()
                .map(record -> new CategoryDTO(
                        (String) record[0],         // Kategori başlığı
                        ((Number) record[1]).longValue()  // Count bilgisi
                ))
                .collect(Collectors.toList());
    }
    public List<Advert> getPopularAdverts(int amount) {
        List<Advert> adverts = advertsRepository.findAllAdverts();

        List<AdvertPopularityDTO> advertPopularityList = adverts.stream().map(advert -> {
                    int tourRequestCount = tourRequestService.countTourRequestsByAdvertId(advert.getId());
                    int popularityScore = methodHelper.calculatePopularityScore(advert.getViewCount(), tourRequestCount);
                    return new AdvertPopularityDTO(advert, popularityScore);
                }).sorted(Comparator.comparingInt(AdvertPopularityDTO::getPopularityScore).reversed())
                .limit(amount)
                .collect(Collectors.toList());

        return advertPopularityList.stream().map(AdvertPopularityDTO::getAdvert).collect(Collectors.toList());
    }
    public Page<Advert> getAllCustomerAdverts(int page, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        return advertsRepository.findAll(pageable);
    }
    public Page<Advert> getAdvertsAdmin(String q, Long categoryId, Long advertTypeId, Double priceStart, Double priceEnd, Integer status, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));

        return advertsRepository.findAdvertsAdmin(q, categoryId, advertTypeId, priceStart, priceEnd, status, pageable);
    }
    public AdvertDTO getAdvertBySlug(String slug) {
        Advert advert = advertsRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Advert not found with slug: " + slug));

        // DTO'yu oluşturma
        AdvertDTO advertDTO = new AdvertDTO();
        advertDTO.setId(advert.getId());
        advertDTO.setTitle(advert.getTitle());
        advertDTO.setSlug(advert.getSlug());

        // Property bilgilerini getirme
        Map<String, String> properties = methodHelper.getPropertiesForAdvert(advert.getId(), advert.getCategory().getId());
        advertDTO.setProperties(properties);

        // Tour Requests bilgilerini getirme
        List<TourRequestDTO> tourRequestDTOs = methodHelper.getTourRequestsForAdvert(advert.getId());
        advertDTO.setTourRequests(tourRequestDTOs);

        // Image bilgilerini getirme
        List<ImageDTO> imageDTOs = methodHelper.getImagesForAdvert(advert.getId());
        advertDTO.setImages(imageDTOs);

        return advertDTO;
    }
    public AdvertDTO getAdvertByIdDTO(Long id) {
        Optional<Advert> advertOptional = advertsRepository.findByIdAndUserIdIsNotNull(id);
        if (advertOptional.isPresent()) {
            Advert advert = advertOptional.get();

            // DTO'yu oluşturma
            AdvertDTO advertDTO = new AdvertDTO();
            advertDTO.setId(advert.getId());
            advertDTO.setTitle(advert.getTitle());
            advertDTO.setSlug(advert.getSlug());

            // Property bilgilerini getirme
            Map<String, String> properties = methodHelper.getPropertiesForAdvert(advert.getId(), advert.getCategory().getId());
            advertDTO.setProperties(properties);

            // Tour Requests bilgilerini getirme
            List<TourRequestDTO> tourRequestDTOs = methodHelper.getTourRequestsForAdvert(advert.getId());
            advertDTO.setTourRequests(tourRequestDTOs);

            // Image bilgilerini getirme
            List<ImageDTO> imageDTOs = methodHelper.getImagesForAdvert(advert.getId());
            advertDTO.setImages(imageDTOs);

            return advertDTO;
        } else {
            throw new AdvertNotFoundException("Advert not found or not associated with a registered user");
        }
    }
    public AdvertDTO getAdvertByIdForAdmin(Long id) {
        Advert advert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advert not found with id: " + id));

        // DTO'yu oluşturma
        AdvertDTO advertDTO = new AdvertDTO();
        advertDTO.setId(advert.getId());
        advertDTO.setTitle(advert.getTitle());
        advertDTO.setSlug(advert.getSlug());

        // Property bilgilerini getirme
        Map<String, String> properties = methodHelper.getPropertiesForAdvert(advert.getId(), advert.getCategory().getId());
        advertDTO.setProperties(properties);

        // Tour Requests bilgilerini getirme
        List<TourRequestDTO> tourRequestDTOs = methodHelper.getTourRequestsForAdvert(advert.getId());
        advertDTO.setTourRequests(tourRequestDTOs);

        // Image bilgilerini getirme
        List<ImageDTO> imageDTOs = methodHelper.getImagesForAdvert(advert.getId());
        advertDTO.setImages(imageDTOs);

        return advertDTO;
    }
    @Transactional
    public AdvertResponse createAdvert(AdvertRequest advertRequest) {
        // Advert objesi oluşturuluyor
        Advert advert = new Advert();
        advert.setTitle(advertRequest.getTitle());
        advert.setDistrict(advertRequest.getDistrict());
        advert.setDescription(advertRequest.getDescription());
        advert.setCategory(advertRequest.getCategory());
        advert.setPrice(advertRequest.getPrice());
        advert.setAdvertType(advertRequest.getAdvertType());
        advert.setCountry(advertRequest.getCountry());
        advert.setLocation(advertRequest.getLocation());
        advert.setCity(advertRequest.getCity());
        advert.setBuilt_in(false);  // Varsayılan değer
        advert.setCreateAt(LocalDateTime.now());  // Mevcut tarih ve zaman
        advert.setStatus(0);
        advert.setSlug(methodHelper.slugGenerate(advertRequest.getTitle()));

        // Advert'i kaydet
        Advert savedAdvert = advertsRepository.save(advert);
        Advert savedAdvertId = new Advert();
        savedAdvertId.setId(savedAdvert.getId());

        // Properties'leri kaydet
        for (PropertyPayload propertyPayload : advertRequest.getProperties()) {
            CategoryPropertyValue propertyValue = new CategoryPropertyValue();
            propertyValue.setAdvert(savedAdvertId);
            propertyValue.setCategoryPropertyKey(propertyPayload.getKeyId());
            propertyValue.setValue(propertyPayload.getValue());
            categoryPropertyValueService.save(propertyValue);
        }
        if (advertRequest.getImages() != null && !advertRequest.getImages().isEmpty()) {
            advertRequest.getImages().forEach(imagePath -> {
                try {
                    // Dosya okuma
                    Path path = Paths.get(imagePath);
                    byte[] imageData = Files.readAllBytes(path);

                    // Image entity'sini oluşturma ve kaydetme
                    Image image = Image.builder()
                            .data(imageData)
                            .name(path.getFileName().toString())
                            .type(Files.probeContentType(path))
                            .advert(savedAdvert)
                            .featured(false) // Varsayılan olarak "featured" false
                            .build();

                    imageService.save(image);

                } catch (IOException e) {
                    throw new RuntimeException("Resim dosyası okunurken hata oluştu: " + imagePath, e);
                }
            });
        }


        AdvertResponse response=advertMapper.mapToAdvertResponse(savedAdvert);
        List<Image> images = imageService.getImagesByAdvertId(advert.getId());
        response.setImages(images);

        // Kaydedilen Advert'in DTO'sunu dön
        return response;
    }
    @Transactional
    public AdvertResponse updateAdvert(Long id, AdvertUpdateRequest advertUpdateRequest) {
        Advert advert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert not found"));

        // Güvenlik katmanında kullanıcı doğrulaması yapıldığı için burada kontrol etmiyoruz.

        if (advert.isBuilt_in()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This advert cannot be updated");
        }

        // Güncelleme alanlarını set ediyoruz
        advert.setTitle(advertUpdateRequest.getTitle());
        advert.setDistrict(advertUpdateRequest.getDistrictId());
        advert.setDescription(advertUpdateRequest.getDescription());
        advert.setCategory(advertUpdateRequest.getCategoryId());
        advert.setPrice(advertUpdateRequest.getPrice());
        advert.setActive(advertUpdateRequest.getIsActive());
        advert.setAdvertType(advertUpdateRequest.getAdvertTypeId());
        advert.setCountry(advertUpdateRequest.getCountryId());
        advert.setCity(advertUpdateRequest.getCityId());

        // Status alanını "PENDING" olarak sıfırlıyoruz
        advert.setStatus(0);

        // Özellikleri güncelliyoruz
        categoryPropertyValueService.updateProperties(advert.getId(), advertUpdateRequest.getProperties());

        // İlanı kaydediyoruz
        Advert updatedAdvert = advertsRepository.save(advert);

        // İlanın güncellenmiş halini alıyoruz, ilişkili tüm verilerin yüklü olduğundan emin oluyoruz
        Advert fetchedAdvert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert not found after update"));

        return advertMapper.mapToAdvertResponse(fetchedAdvert);
    }
    @Transactional
    public AdvertResponse updateAdvertByAdminOrManager(Long id, AdvertUpdateRequest advertUpdateRequest) {
        Advert advert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert not found"));

        // Güvenlik katmanında kullanıcı doğrulaması yapıldığı için burada kontrol etmiyoruz.

        if (advert.isBuilt_in()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This advert cannot be updated");
        }

        // Güncelleme alanlarını set ediyoruz
        advert.setTitle(advertUpdateRequest.getTitle());
        advert.setDistrict(advertUpdateRequest.getDistrictId());
        advert.setDescription(advertUpdateRequest.getDescription());
        advert.setCategory(advertUpdateRequest.getCategoryId());
        advert.setPrice(advertUpdateRequest.getPrice());
        advert.setAdvertType(advertUpdateRequest.getAdvertTypeId());
        advert.setCountry(advertUpdateRequest.getCountryId());
        advert.setCity(advertUpdateRequest.getCityId());

        // Status alanını "PENDING" olarak sıfırlıyoruz
        advert.setStatus(0);

        // Özellikleri güncelliyoruz
        categoryPropertyValueService.updateProperties(advert.getId(), advertUpdateRequest.getProperties());

        // İlanı kaydediyoruz
        Advert updatedAdvert = advertsRepository.save(advert);

        // İlanın güncellenmiş halini alıyoruz, ilişkili tüm verilerin yüklü olduğundan emin oluyoruz
        Advert fetchedAdvert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Advert not found after update"));

        return advertMapper.mapToAdvertResponse(fetchedAdvert);
    }
    @Transactional
    public Advert deleteAdvertById(Long id) {
        // İlanın builtIn özelliği true ise silinemez
        if (advertsRepository.existsByIdAndBuiltInTrue(id)) {
            throw new IllegalArgumentException("Bu ilan silinemez, çünkü builtIn özelliği true.");
        }

        // İlanı bul
        Advert advert = advertsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advert not found with id: " + id));

        // İlanla ilişkili diğer verileri sil
        tourRequestService.deleteByAdvertId(id);
        favoriteService.deleteByAdvertId(id);
        imageService.deleteByAdvertId(id);
        categoryPropertyValueService.deleteByAdvertId(id);
        logService.deleteByAdvertId(id);

        // İlanı sil
        advertsRepository.delete(advert);

        return advert;
    }
}


