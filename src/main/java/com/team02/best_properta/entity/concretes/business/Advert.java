package com.team02.best_properta.entity.concretes.business;

import com.team02.best_properta.entity.concretes.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "adverts") // Veritabanı tablosunun adı "adverts" olarak belirtildi.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key, otomatik artan bir sayı olarak oluşturulur.

    @Column(nullable = false, length = 150)
    private String title; // Başlık, null olamaz, en az 5 en fazla 150 karakter uzunluğunda olmalı.

    @Column(length = 300)
    private String description; // Açıklama, nullable, maksimum 300 karakter uzunluğunda olabilir.

    @Column(nullable = false, length = 200)
    private String slug; // Slug, null olamaz, en az 5, en fazla 200 karakter, title'ı URL'ye uygun hale getirilmiş
    // olarak temsil eder.

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // Fiyat, null olamaz, hassas hesaplamalar için BigDecimal kullanıldı.

    @Column(nullable = false, columnDefinition = "int default 0")
    private int status; // İlan durumu, null olamaz, varsayılan değer 0 (0: Pending, 1: Activated, 2: Rejected)

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean built_in; // Dahili mi, null olamaz, varsayılan değer false, 1: Silinemez veya güncellenemez,
    // varsayılan 0.

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isActive; // İlan aktif mi, null olamaz, varsayılan değer true, false: Yayınlanmamış,
    // true: Yayınlanmış.

    @Column(nullable = false)
    private LocalDateTime createAt; // İlanın oluşturulma tarihi, null olamaz. Örnek format: "1990-10-25T10:35:25Z"

    @Column(nullable = false, columnDefinition = "int default 0")
    private int viewCount; // İlanın detay sayfası ziyaret sayısı, null olamaz, varsayılan değer 0.

    @Column(nullable = true)
    private LocalDateTime updateAt; // İlanın güncellenme tarihi, nullable olarak belirtildi.

    @Column(length = 255)
    private String location; // Google embed kodu olarak konum bilgisi, varchar tipinde.

    @ManyToOne(fetch = FetchType.LAZY) // advert_type_id, foreign key olarak advert_types tablosuna bağlanır.
    @JoinColumn(name = "advert_type_id", nullable = false)
    private AdvertType advertType;

    @ManyToOne(fetch = FetchType.LAZY) // country_id, foreign key olarak countries tablosuna bağlanır.
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY) // city_id, foreign key olarak cities tablosuna bağlanır.
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY) // district_id, foreign key olarak districts tablosuna bağlanır.
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY) // user_id, foreign key olarak users tablosuna bağlanır.
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY) // category_id, foreign key olarak categories tablosuna bağlanır.
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
