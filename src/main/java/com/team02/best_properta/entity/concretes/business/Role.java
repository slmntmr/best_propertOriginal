package com.team02.best_properta.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType roleType;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // Döngüsel bağımlılığı engeller
    private List<Users> users;
}
