package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "images") // Bu varlığın veritabanındaki tablo adını "images" olarak belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.



public class Image {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Resmin benzersiz kimliği (PK).

    @Lob // Bu alanın büyük veri tipinde (BLOB) olduğunu belirtir.
    @Column(name = "data", nullable = false) // Veritabanındaki sütun adını belirtir ve boş olamayacağını belirtir.
    private byte[] data; // Resmin verisi (binary format).

    @Column(name = "name", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private String name; // Resmin adı.

    @Column(name = "type") // Bu alanın boş olabileceğini belirtir.
    private String type; // Resmin tipi (örneğin: "image/jpeg").

    @Column(name = "featured", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private Boolean featured; // Resmin öne çıkan bir resim olup olmadığını belirten bayrak.

    @ManyToOne // Image ile Advert arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "advert_id", nullable = false) // Bu alanın advert_id ile ilişkili olduğunu belirtir ve boş olamayacağını belirtir.
    private Advert advert; // Resmin ait olduğu ilan.
}
