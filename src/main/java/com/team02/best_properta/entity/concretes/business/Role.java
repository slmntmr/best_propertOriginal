package com.team02.best_properta.entity.concretes.business;

import com.team02.best_properta.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "role") // Bu varlığın veritabanındaki tablo adını "role" olarak belirtir.
@Getter // Lombok tarafından getter metodlarını otomatik olarak oluşturur.
@Setter // Lombok tarafından setter metodlarını otomatik olarak oluşturur.
@AllArgsConstructor // Lombok tarafından tüm alanları içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok tarafından parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok tarafından Builder tasarım desenini kullanarak nesne oluşturmayı sağlar, var olan bir
// nesneyi temel alarak yeni bir nesne oluşturmayı da destekler.
public class Role {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Rolün benzersiz kimliği (PK).

    @Column(name = "role_name", nullable = false, length = 50) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String roleName; // Rolün ismini tutar (varchar, Not null).

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType roleType;


}

