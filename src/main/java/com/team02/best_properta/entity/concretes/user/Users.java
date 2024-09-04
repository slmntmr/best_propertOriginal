package com.team02.best_properta.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team02.best_properta.entity.concretes.business.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "t_user") // Bu varlığın veritabanındaki tablo adını "t_user" olarak belirtir.
@Getter // Lombok tarafından getter metodlarını otomatik olarak oluşturur.
@Setter // Lombok tarafından setter metodlarını otomatik olarak oluşturur.
@AllArgsConstructor // Lombok tarafından tüm alanları içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok tarafından parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok tarafından Builder tasarım desenini kullanarak nesne oluşturmayı sağlar, var olan bir
// nesneyi temel alarak yeni bir nesne oluşturmayı da destekler.
public class Users {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Kullanıcının benzersiz kimliği (PK).

    @Column(name = "first_name", nullable = false, length = 30) // Bu alanın boş olamayacağını, ve maksimum uzunluğunu belirtir.
    private String firstName; // Kullanıcının ilk adı (varchar, min:2, max:30).

    @Column(name = "last_name", nullable = false, length = 30) // Bu alanın boş olamayacağını, ve maksimum uzunluğunu belirtir.
    private String lastName; // Kullanıcının soyadı (varchar, min:2, max:30).

    @Column(name = "email", nullable = false, unique = true, length = 80) // Bu alanın boş olamayacağını, eşsiz olması gerektiğini ve maksimum uzunluğunu belirtir.
    private String email; // Kullanıcının e-posta adresi (varchar, min:10, max:80, e-posta formatında).

    @Column(name = "phone", nullable = false, length = 20) // Bu alanın boş olamayacağını belirtir.
    private String phone; // Kullanıcının telefon numarası (varchar).

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Bu alanın sadece JSON'dan Java nesnesine dönüştürülürken
    // kullanılacağını belirtir, JSON çıktısında yer almaz.
    private String password_hash; // Kullanıcının şifresi (güvenlik amacıyla JSON'da gizlenir).

    @Column(name = "reset_password_code", length = 100) // Bu alanın boş olabileceğini belirtir.
    private String resetPasswordCode; // Şifre sıfırlama kodu (varchar).

    @Column(name = "built_in") // Bu alanın boş olup olmadığını belirtir.
    private Boolean builtIn; // Kullanıcının yerleşik olup olmadığını belirten bayrak (Boolean).

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Kullanıcının oluşturulma tarihi (DateTime), ex: 1990-10-25T10:35:25Z.

    @Column(name = "update_at") // Bu alanın boş olabileceğini belirtir.
    private LocalDateTime updateAt; // Kullanıcının güncellenme tarihi (DateTime, Nullable).

    @OneToMany(mappedBy = "user") // Kullanıcı ile UserRole arasında bire çok ilişki olduğunu belirtir.
    private List<UserRole> userRoles; // Kullanıcının sahip olduğu roller listesi.


    @OneToOne // Her kullanıcının bir rolü olabilir.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Bu alanın sadece JSON'dan Java nesnesine dönüştürülürken
    // kullanılacağını belirtir, JSON çıktısında yer almaz.
    private Role role;

    @OneToMany(mappedBy = "user") // Kullanıcı ile Advert arasında bire çok ilişki olduğunu belirtir.
    private List<Advert> adverts; // Kullanıcının oluşturduğu ilanlar listesi.

    @OneToMany(mappedBy = "user") // Kullanıcı ile Favorite arasında bire çok ilişki olduğunu belirtir.
    private List<Favorite> favorites; // Kullanıcının favori ilanları listesi.

    @OneToMany(mappedBy = "user") // Kullanıcı ile Log arasında bire çok ilişki olduğunu belirtir.
    private List<Log> logs; // Kullanıcının yaptığı işlemlerin log kayıtları listesi.

    @OneToMany(mappedBy = "ownerUser") // Kullanıcı (sahip) ile TourRequest arasında bire çok ilişki olduğunu belirtir.
    private List<TourRequest> ownedTourRequests; // Kullanıcının (sahip olarak) yaptığı tur talepleri listesi.

    @OneToMany(mappedBy = "guestUser") // Kullanıcı (misafir) ile TourRequest arasında bire çok ilişki olduğunu belirtir.
    private List<TourRequest> guestTourRequests; // Kullanıcının (misafir olarak) aldığı tur talepleri listesi.
}
