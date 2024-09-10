package com.team02.best_properta.repository.user;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {


    boolean existsByEmail(String email); // E-posta adresine göre bir kullanıcının var olup olmadığını kontrol eder.

    @Query(value = "SELECT COUNT(u) FROM Users u WHERE u.role.roleType = ?1")
    long countAdmin(RoleType roleType);


    Optional<Users> findByEmail(String email); // E-posta adresine göre kullanıcıyı bulur.


    Optional<Users> findByResetPasswordCode(String resetPasswordCode);


    Page<Users> findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContaining(
            String firstName, String lastName, String email, String phone, Pageable pageable);

  // E-posta adresine göre bir kullanıcının var olup olmadığını kontrol eder.




    @Query("SELECT COUNT(u) FROM Users u")
    long countUsers();

    @Query("SELECT u FROM Users u")
    List<Users> getAllUsers();

    @Query("SELECT u FROM Users u WHERE u.role = :role")
    List<Users> findByRole(@Param("role") String role);



}
