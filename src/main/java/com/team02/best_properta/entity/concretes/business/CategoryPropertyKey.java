package com.team02.best_properta.entity.concretes.business;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // Bu sınıfın bir JPA varlığı olduğunu belirtir.
@Table(name = "category_property_key") // Veritabanındaki tablo adını belirtir.
@Data
// Lombok'un @Data anotasyonu, bu sınıf için getter ve setter yöntemlerini, toString, equals ve hashCode gibi temel metodları otomatik olarak oluşturur.
@AllArgsConstructor
// Lombok'un @AllArgsConstructor anotasyonu, sınıfın tüm alanlarını içeren bir yapıcı (constructor) oluşturur.
@NoArgsConstructor // Lombok'un @NoArgsConstructor anotasyonu, parametresiz bir yapıcı (constructor) oluşturur.
@Builder(toBuilder = true) // Lombok'un @Builder anotasyonu, Builder tasarım desenini destekler. Bu, nesneleri daha esnek ve okunabilir şekilde oluşturmayı sağlar. "toBuilder = true" seçeneği, mevcut nesneden yeni bir builder oluşturulmasını sağlar.
@Getter
@Setter



public class CategoryPropertyKey {

    @Id // Bu alanın veritabanında birincil anahtar olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Birincil anahtarın otomatik olarak ve benzersiz bir şekilde oluşturulacağını belirtir.
    @Column(name = "id") // Veritabanındaki sütun adını belirtir.
    private Long id; // Kategorinin özellik anahtarının benzersiz kimliği (PK).

    @Column(name = "name", nullable = false, length = 80) // Bu alanın boş olamayacağını, minimum ve maksimum uzunluğunu belirtir.
    private String name; // Özellik anahtarının adı (string).

    @Column(name = "built_in") // Bu alanın boş olup olmadığını belirtir.
    private Boolean builtIn; // Özellik anahtarının yerleşik olup olmadığını belirten bayrak (boolean).

    @Column(name = "type")
    private String type;

    @ManyToOne // CategoryPropertyKey ile Category arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "category_id", nullable = false) // Bu alanın category_id ile ilişkili olduğunu belirtir.
    private Category category; // Özellik anahtarının ait olduğu kategori (number).

    @OneToMany(mappedBy = "categoryPropertyKey" ,cascade = CascadeType.ALL) // CategoryPropertyKey ile CategoryPropertyValue arasında bire çok ilişki olduğunu belirtir.
    private List<CategoryPropertyValue> categoryPropertyValues; // Bu anahtara ait değerlerin listesi (string).
}