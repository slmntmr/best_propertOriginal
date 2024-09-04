package com.team02.best_properta.controller.user;

import com.team02.best_properta.payload.request.business.ForgotPasswordRequest;
import com.team02.best_properta.payload.request.business.ResetPasswordRequest;
import com.team02.best_properta.payload.request.user.*;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.payload.response.user.*;
import com.team02.best_properta.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;


@RequiredArgsConstructor
@RestController // Bu sınıfın bir RESTful web hizmeti denetleyicisi olduğunu belirtir.
@RequestMapping("/user") // Bu sınıfın "/register" yolundaki istekleri işleyeceğini belirtir.
public  class UserController {

    // Constructor ile bağımlılıkların enjekte edilmesini sağlar.
    private final UserService userService;


    // F01 Bu metod, kullanıcıyı kimlik doğrulama işlemini gerçekleştirir.
    //  LoginRequest nesnesi içindeki email ve şifre bilgilerini kullanarak kullanıcıyı doğrular.
    //  Kimlik doğrulama başarılı olursa, bir JWT token oluşturur ve geri döner.
    @PostMapping("/login")//  http://localhost:8080/user/login + POST + JSON
    @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }





    //**********************************************************************************************************************************************************




    // F02 Kullanıcıyı kaydeder
    @PostMapping("/register/{userRole}") // http://localhost:8080/user/register/{userRole} + POST + JSON
    @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<ResponseMessage<UserResponse>> register(@Valid @RequestBody UserRequest userRequest,
                                                                  @PathVariable String userRole) {
        return ResponseEntity.ok(userService.register(userRequest, userRole));
    }

    //**********************************************************************************************************************************************************

    // F03  Şifre sıfırlama kodu talebi oluşturur ve e-posta gönderir.
    @PostMapping("/forgot-password") // http://localhost:8080/user/forgot-password + POST + JSON
    @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        userService.forgotPassword(forgotPasswordRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //**********************************************************************************************************************************************************



    // F04 Şifre sıfırlama kodunu doğrular ve yeni şifreyi ayarlar.
    @PostMapping("/reset-password")// http://localhost:8080/user/reset-password + POST + JSON
    @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //**********************************************************************************************************************************************************

    // F05  Kimliği doğrulanmış kullanıcıyı getirir.
    @GetMapping("/me")// http://localhost:8080/user/me + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<AuthenticatedUserResponse> getAuthenticatedUser() {
        AuthenticatedUserResponse userResponse = userService.getAuthenticatedUser();
        return ResponseEntity.ok(userResponse);
    }

    //**********************************************************************************************************************************************************


    // F06 Kimliği doğrulanmış kullanıcının bilgilerini günceller
    @PutMapping("/info") // http://localhost:8080/user/info + PUT + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<UserUpdateResponse> updateAuthenticatedUser(@RequestBody UserUpdateRequest updateRequest) {
        UserUpdateResponse userUpdateResponse = userService.updateAuthenticatedUser(updateRequest);
        return ResponseEntity.ok(userUpdateResponse);
    }

    //**********************************************************************************************************************************************************


    // F07 Kimliği doğrulanmış kullanıcının şifresini günceller.
    @PatchMapping("/password") // http://localhost:8080/user/password + PATCH + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','CUSTOMER')")
    public ResponseEntity<UserPasswordUpdateResponse> updatePassword(
            @RequestBody UserPasswordUpdateRequest updateRequest) {
        UserPasswordUpdateResponse response = userService.updateAuthenticatedUserPassword(updateRequest);
        return ResponseEntity.ok(response);
    }


    //**********************************************************************************************************************************************************


    // F08  Kimliği doğrulanmış kullanıcının hesabını siler.
    @DeleteMapping("/auth") // http://localhost:8080/user/auth + DELETE + JSON
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<?> deleteAuthenticatedUser(@RequestBody UserDeleteRequest deleteRequest) {
        userService.deleteAuthenticatedUser(deleteRequest.getPassword());
        return ResponseEntity.noContent().build();
    }

    //**********************************************************************************************************************************************************

    // F09 Sayfalama ve sıralama seçenekleri ile admin kullanıcıları getirir.
    @GetMapping("/admin")// http://localhost:8080/user/admin + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<UserGetPageableResponse> getAdminUsers(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createDate") String sort,
            @RequestParam(defaultValue = "desc") String type) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));
        return userService.getAdminUsers(q, pageable);
    }



    //**********************************************************************************************************************************************************

    // F10 Belirtilen ID'ye sahip kullanıcıyı getirir.
    @GetMapping("/users/{id}/admin") // http://localhost:8080/user/users/{id}/admin + GET
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public UserGetIdResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //**********************************************************************************************************************************************************



    // F11 Kimliği doğrulanmış kullanıcının bilgilerini günceller.
    @PutMapping("/auth") // http://localhost:8080/user/auth + PUT + JSON
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public UserUpdateWithIdResponse updateAuthenticatedUser(
            @RequestBody UserUpdateWithIdRequest updateRequest,
            Authentication authentication) {

        return userService.updateUser(updateRequest, authentication);
    }

    //**********************************************************************************************************************************************************


    // F12  Belirtilen ID'ye sahip kullanıcıyı siler.
    @DeleteMapping("/{id}/admin")// http://localhost:8080/user/{id}/admin + DELETE
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public UserDeleteResponse deleteUser(@PathVariable Long id, Authentication authentication) {
        return userService.deleteUserById(id, authentication);
    }








}
