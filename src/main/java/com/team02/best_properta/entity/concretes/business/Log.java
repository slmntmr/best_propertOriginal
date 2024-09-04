package com.team02.best_properta.entity.concretes.business;

import com.team02.best_properta.entity.concretes.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "logs") // Bu varlığın veritabanındaki tablo adını "logs" olarak belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.



public class Log {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Logun benzersiz kimliği (PK).

    @Column(name = "log") // Veritabanındaki sütun adını belirtir.
    private String log; // Teklif için yapılan eylemi temsil eden basit bir kod.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Veritabanındaki user_id sütununa karşılık gelir.
    private Users user; // Logu oluşturan kullanıcı (FK).

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false) // Veritabanındaki advert_id sütununa karşılık gelir.
    private Advert advert; // Log ile ilişkili ilan (FK).

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Logun oluşturulma tarihi.

}