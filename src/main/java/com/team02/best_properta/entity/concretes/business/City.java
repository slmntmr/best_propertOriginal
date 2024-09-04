package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "cities") // Bu varlığın veritabanındaki tablo adını "cities" olarak belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.



public class City {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Şehrin benzersiz kimliği (PK).

    @Column(name = "name", nullable = false, length = 30) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String name; // Şehrin adı.

    @ManyToOne // City ile Country arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "country_id", nullable = false) // Bu alanın country_id ile ilişkili olduğunu belirtir.
    private Country country; // Şehrin ait olduğu ülke.

    @OneToMany(mappedBy = "city") // City ile Advert arasında bire çok ilişki olduğunu belirtir.
    private List<Advert> adverts; // Bu şehirde bulunan ilanların listesi.

    @OneToMany(mappedBy = "city") // City ile District arasında bire çok ilişki olduğunu belirtir.
    private List<District> districts; // Bu şehirde bulunan ilçelerin listesi.
}
