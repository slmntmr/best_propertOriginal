package com.team02.best_properta.contactmessage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data // Lombok, bu anotasyon ile bu sınıf için getter, setter, toString, equals ve hashCode metodlarını otomatik olarak oluşturur.
@AllArgsConstructor // Lombok, tüm alanları içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok, Builder tasarım desenini destekler, bu sayede nesneler daha esnek ve okunabilir şekilde oluşturulabilir.

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir ve veritabanında bir tabloya karşılık gelir.
@Table(name = "contact_messages") // Veritabanında kullanılacak tablo adı.
public class ContactMessage implements Serializable { // 'Serializable' arayüzü, bu sınıfın nesnelerinin seri hale getirilebilir olmasını sağlar.

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adı.
    private Long id; // İletişim mesajının benzersiz kimliğini temsil eden alan.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Column(name = "first_name", nullable = false, length = 30) // Veritabanındaki sütun adı ve özellikleri.
    private String firstName; // Mesajı gönderen kişinin adı.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Column(name = "last_name", nullable = false, length = 30) // Veritabanındaki sütun adı ve özellikleri.
    private String lastName; // Mesajı gönderen kişinin soyadı.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Column(name = "email", nullable = false, length = 60) // Veritabanındaki sütun adı ve özellikleri.
    private String email; // Mesajı gönderen kişinin e-posta adresi.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Column(name = "message", nullable = false, length = 300) // Veritabanındaki sütun adı ve özellikleri.
    private String message; // Gönderilen mesajın içeriği.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Column(name = "status", nullable = false) // Veritabanındaki sütun adı ve özellikleri.
    private Integer status; // Mesajın durumunu temsil eden alan.

    @NotNull // Bu alanın boş olamayacağını belirtir.
    @Builder.Default // Varsayılan değeri ayarlar.
    @Column(name = "created_at", nullable = false) // Veritabanındaki sütun adı ve özellikleri.
    private LocalDateTime createdAt = LocalDateTime.now(); // Mesajın oluşturulma tarihi ve saati.
}
