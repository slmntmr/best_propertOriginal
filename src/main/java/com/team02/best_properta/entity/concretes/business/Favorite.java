package com.team02.best_properta.entity.concretes.business;

import com.team02.best_properta.entity.concretes.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "favorite") // Veritabanındaki tablo adını belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.



public class Favorite {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Favorinin benzersiz kimliği (PK).

    @ManyToOne // Favorite ile Users arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "user_id", nullable = false) // Bu alanın user_id ile ilişkili olduğunu belirtir.
    private Users user; // Favoriyi ekleyen kullanıcı (FK).

    @ManyToOne // Favorite ile Advert arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "advert_id", nullable = false) // Bu alanın advert_id ile ilişkili olduğunu belirtir.
    private Advert advert; // Favori ilan (FK).

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Favori eklenme tarihi (DateTime), ex: 1990-10-25T10:35:25Z.
}
