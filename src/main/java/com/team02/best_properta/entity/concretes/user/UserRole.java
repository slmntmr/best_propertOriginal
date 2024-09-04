package com.team02.best_properta.entity.concretes.user;

import com.team02.best_properta.entity.concretes.business.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "roles") // Bu varlığın veritabanındaki tablo adını "roles" olarak belirtir.
@Data // Lombok tarafından getter, setter, toString, hashCode ve equals metodlarını otomatik olarak oluşturur.
@AllArgsConstructor // Lombok tarafından tüm alanları içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok tarafından parametresiz bir yapıcı (constructor) oluşturur.
@Builder // Lombok tarafından Builder tasarım desenini kullanarak nesne oluşturmayı sağlar.
public class UserRole {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // UserRole'ın benzersiz kimliği (PK).

    @ManyToOne // UserRole ile User arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "user_id", nullable = false) // Bu alanın user_id ile ilişkili olduğunu belirtir.
    private Users user; // Role sahip kullanıcı.

    @ManyToOne // UserRole ile Role arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "role_id", nullable = false) // Bu alanın role_id ile ilişkili olduğunu belirtir.
    private Role role; // Kullanıcının sahip olduğu rol.
}
