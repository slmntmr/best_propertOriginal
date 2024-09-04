package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "districts") // Bu varlığın veritabanındaki tablo adını "districts" olarak belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.



public class District {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // İlçenin benzersiz kimliği (PK).

    @Column(name = "name", nullable = false, length = 30) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String name; // İlçenin adı.

    @ManyToOne // District ile City arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "city_id", nullable = false) // Bu alanın city_id ile ilişkili olduğunu belirtir.
    private City city; // İlçenin ait olduğu şehir.

    @OneToMany(mappedBy = "district") // District ile Advert arasında bire çok ilişki olduğunu belirtir.
    private List<Advert> adverts; // Bu ilçede bulunan ilanların listesi.
}
