package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "category_property_value") // Veritabanındaki tablo adını belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.


public class CategoryPropertyValue {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Özellik değerinin benzersiz kimliği (PK).

    @Column(name = "value", nullable = false, length = 100) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String value; // Özellik değerinin adı (varchar).

    @ManyToOne // CategoryPropertyValue ile CategoryPropertyKey arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "category_property_key_id", nullable = false ) // Bu alanın category_property_key_id ile ilişkili olduğunu belirtir.
    private CategoryPropertyKey categoryPropertyKey; // Özellik değerinin ait olduğu anahtar (FK).

    @ManyToOne // CategoryPropertyValue ile Advert arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "advert_id", nullable = false) // Bu alanın advert_id ile ilişkili olduğunu belirtir.
    private Advert advert; // Özellik değerinin ait olduğu ilan (FK).
}