package com.team02.best_properta.entity.concretes.business;

import com.team02.best_properta.entity.concretes.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "tour_requests") // Bu varlığın veritabanındaki tablo adını "tour_requests" olarak belirtir.
@Data // Getter ve Setter'ları otomatik olarak oluşturur.
@NoArgsConstructor // Parametresiz bir yapıcı metodu oluşturur.
@AllArgsConstructor // Parametreli bir yapıcı metodu oluşturur.
@Builder(toBuilder = true) // Builder desenini destekler.
public class TourRequest {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Tur talebinin benzersiz kimliği (PK).

    @Column(name = "tour_date", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime tourDate; // Turun gerçekleşeceği tarih.

    @Column(name = "tour_time", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime tourTime; // Turun gerçekleşeceği saat.

    @Column(name = "status", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private Integer status; // Tur talebinin durumu (int). Durum kodları 0: Beklemede, 1: Onaylandı, 2: Reddedildi, 3: İptal Edildi.

    @ManyToOne // TourRequest ile Advert arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "advert_id", nullable = false) // Bu alanın advert_id ile ilişkili olduğunu belirtir.
    private Advert advert; // Tur talebinin ait olduğu ilan.

    @ManyToOne // TourRequest ile User arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "owner_user_id", nullable = false) // Bu alanın owner_user_id ile ilişkili olduğunu belirtir.
    private Users ownerUser; // Tur talebinin sahibi olan kullanıcı.

    @ManyToOne // TourRequest ile User arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "guest_user_id") // Bu alanın guest_user_id ile ilişkili olduğunu belirtir.
    private Users guestUser; // Tur talebinde bulunan misafir kullanıcı.

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Tur talebinin oluşturulma tarihi.

    @Column(name = "update_at") // Bu alanın boş olabileceğini belirtir.
    private LocalDateTime updateAt; // Tur talebinin güncellenme tarihi.
}
