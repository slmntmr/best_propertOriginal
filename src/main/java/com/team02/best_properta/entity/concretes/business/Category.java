package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "category") // Veritabanındaki tablo adını belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.

public class Category {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id;

    @Column(name = "title", nullable = false, length = 150) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String title; // Kategorinin başlığı.

    @Column(name = "icon", nullable = false, length = 50) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String icon; // Kategorinin ikonu.

    @Column(name = "built_in") // Bu alanın boş olup olmadığını belirtir.
    private Boolean builtIn; // Kategorinin yerleşik olup olmadığını belirten bayrak.

    @Column(name = "seq", nullable = false, columnDefinition = "int default 0") // Bu alanın boş olamayacağını ve varsayılan değerini belirtir.
    private Integer seq; // Kategorinin sırası.

    @Column(name = "slug", nullable = false, length = 200) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String slug; // Kategorinin URL dostu kısa adı.

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true") // Bu alanın boş olamayacağını ve varsayılan değerini belirtir.
    private Boolean isActive; // Kategorinin aktif olup olmadığını belirten bayrak.

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Kategorinin oluşturulma tarihi.

    @Column(name = "update_at") // Bu alanın boş olabileceğini belirtir.
    private LocalDateTime updateAt; // Kategorinin güncellenme tarihi.

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Category ile Advert arasında bire çok ilişki olduğunu belirtir.
    private List<Advert> adverts; // Bu kategoriye ait ilanların listesi.

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Category ile CategoryPropertyKey arasında bire çok ilişki olduğunu belirtir.
    private List<CategoryPropertyKey> categoryPropertyKeys; // Bu kategoriye ait özellik anahtarlarının listesi.
}