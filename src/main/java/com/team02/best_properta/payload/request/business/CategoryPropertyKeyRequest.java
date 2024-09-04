package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class CategoryPropertyKeyRequest {

    @Column(name = "value", nullable = false, length = 100) // Bu alanın boş olamayacağını ve maksimum uzunluğunu belirtir.
    private String value; // Özellik değerinin adı (varchar).

    private String name;
    private String type; // Property'nin tipi
    @ManyToOne // CategoryPropertyValue ile CategoryPropertyKey arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "category_property_key_id", nullable = false ) // Bu alanın category_property_key_id ile ilişkili olduğunu belirtir.
    private CategoryPropertyKey categoryPropertyKey; // Özellik değerinin ait olduğu anahtar (FK).

    @ManyToOne // CategoryPropertyValue ile Advert arasında çoktan bire ilişki olduğunu belirtir.
    @JoinColumn(name = "advert_id", nullable = false) // Bu alanın advert_id ile ilişkili olduğunu belirtir.
    private Advert advert; // Özellik değerinin ait olduğu ilan (FK).
    private boolean builtIn;
    @OneToMany(mappedBy = "categoryPropertyKey" ,cascade = CascadeType.ALL) // CategoryPropertyKey ile CategoryPropertyValue arasında bire çok ilişki olduğunu belirtir.
    private List<CategoryPropertyValue> categoryPropertyValues; // Bu anahtara ait değerlerin listesi (string).

}