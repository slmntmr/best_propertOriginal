package com.team02.best_properta.service.user;


import com.team02.best_properta.payload.request.user.*;
import com.team02.best_properta.security.jwt.JwtUtils;
import org.springframework.data.domain.Pageable;
import com.team02.best_properta.entity.concretes.user.Users;
import org.springframework.data.domain.Page;
import com.team02.best_properta.entity.enums.RoleType;
import com.team02.best_properta.exception.BadRequestException;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.UsersMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.messages.SuccessMessages;
import com.team02.best_properta.payload.request.business.ForgotPasswordRequest;
import com.team02.best_properta.payload.request.business.ResetPasswordRequest;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.payload.response.user.*;
import com.team02.best_properta.repository.user.UserRepository;
import com.team02.best_properta.service.business.*;
import com.team02.best_properta.utility.CodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service // Bu sınıfın bir servis sınıfı olduğunu belirtir.
public  class UserService {
    private final UsersMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private  final CodeGenerator codeGenerator;
    private final EmailService emailService;
    private final AdvertsService advertsService;
    private final TourRequestsService tourRequestsService;
    private final FavoritesService favoritesService;
    private final LogsService logsService;
    private final AuthenticationManager authenticationManager; // Ekledik
    private final JwtUtils jwtUtils; // Ekledik


    // F01


    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        return new LoginResponse(jwtToken);
    }






    //******************************************************************************************



    public ResponseMessage<UserResponse> register(UserRequest userRequest, String userRole) {
        // 1. Gelen kullanıcı talebi için e-posta adresinin veritabanında zaten var olup olmadığını kontrol ediyoruz.
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            // Eğer e-posta zaten kullanılıyorsa bir hata fırlatıyoruz.
            throw new IllegalStateException("Email is already taken");
        }

        // 2. DTO-->POJO
        Users user = userMapper.mapUserRequestToUser(userRequest);


        //!!! Rol bilgisini setliyoruz
        if (userRole.equalsIgnoreCase(RoleType.ADMIN.name())) {

            //!!! Eğer kullanıcı adı "Admin" ise, built_in değerini true yapıyoruz.
            if (Objects.equals(userRequest.getEmail(), "admin@admin.com")) {
                user.setBuiltIn(true);
            }
            //!!! Kullanıcıya admin rolü veriliyor.
            user.setRole(roleService.getUserRole(RoleType.ADMIN));

        } else if (userRole.equalsIgnoreCase("Dean")) {
            //!!! Kullanıcıya "Dean" rolü veriliyor.
            user.setRole(roleService.getUserRole(RoleType.MANAGER));

        } else if (userRole.equalsIgnoreCase("Customer")) {
            //!!! Kullanıcıya "Customer" rolü veriliyor.
            user.setRole(roleService.getUserRole(RoleType.CUSTOMER));

        } else if (userRole.equalsIgnoreCase("Anonymous")) {
            //!!! Kullanıcıya "Anonymous" rolü veriliyor.
            user.setRole(roleService.getUserRole(RoleType.ANONYMOUS));

        } else {
            //!!! Eğer geçerli bir rol bulunamazsa, hata fırlatılır.
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_USERROLE_MESSAGE, userRole));
        }

        if (user.getPassword_hash() == null || user.getPassword_hash().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));


        //!!! Kullanıcı veritabanına kaydedilir.
        Users savedUser = userRepository.save(user);


        //!!! Başarılı bir yanıt mesajı oluşturulur ve kaydedilen kullanıcı bilgileri döndürülür.
        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_CREATED)
                .object(userMapper.mapUserToUserResponse(savedUser))
                .build();

    }


    //******************************************************************************************
    //!!! Runner tarafi icin yazildi
    public long countAllAdmins() {
        return userRepository.countAdmin(RoleType.ADMIN);
    }

//******************************************************************************************

    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        Optional<Users> userOptional = userRepository.findByEmail(forgotPasswordRequest.getEmail());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            String resetCode = codeGenerator.generateResetCode(); // 6 karakterlik reset kodu üretir
            user.setResetPasswordCode(resetCode);
            userRepository.save(user);

            // E-posta gönderimi
            String message = "Your password reset code is: " + resetCode;
            emailService.sendEmail(user.getEmail(), "Password Reset Request", message);
        }
    }

    //******************************************************************************************



    // Şifre sıfırlama işlemi
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<Users> userOptional = userRepository.findByResetPasswordCode(resetPasswordRequest.getResetCode());

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Yeni şifreyi şifrele
            String encodedPassword = passwordEncoder.encode(resetPasswordRequest.getNewPassword());
            user.setPassword_hash(encodedPassword);
            user.setResetPasswordCode(null); // Kodun bir kez kullanılabilir olduğunu varsayarak sıfırla
            userRepository.save(user);
        } else {
            throw new BadRequestException("The reset code is not valid");
        }
    }

    //******************************************************************************************

    public AuthenticatedUserResponse getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            Users user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            return userMapper.mapUserToAuthenticatedUserResponse(user);
        }

        throw new ResourceNotFoundException("User not found");
    }



    //******************************************************************************************




    public UserUpdateResponse updateAuthenticatedUser(UserUpdateRequest updateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = (Users) authentication.getPrincipal();

        if (currentUser.getBuiltIn()) {
            throw new IllegalStateException("Cannot update built-in user.");
        }

        currentUser.setFirstName(updateRequest.getFirstName());
        currentUser.setLastName(updateRequest.getLastName());
        currentUser.setPhone(updateRequest.getPhone());

        Users updatedUser = userRepository.save(currentUser);

        return userMapper.mapUserToUserUpdateResponse(updatedUser);
    }


    //******************************************************************************************



    public UserPasswordUpdateResponse updateAuthenticatedUserPassword(UserPasswordUpdateRequest updateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = (Users) authentication.getPrincipal();

        if (currentUser.getBuiltIn()) {
            throw new IllegalStateException("Cannot update built-in user.");
        }

        // DTO'dan mevcut kullanıcıyı güncelle
        currentUser = userMapper.mapUserPasswordUpdateRequestToUser(updateRequest, currentUser);

        // Kullanıcıyı güncelle ve kaydet
        Users updatedUser = userRepository.save(currentUser);

        // Güncellenmiş kullanıcıyı DTO'ya dönüştür ve yanıt olarak döndür
        return userMapper.mapUserToUserPasswordUpdateResponse(updatedUser);
    }


    //******************************************************************************************



    @Transactional
    public void deleteAuthenticatedUser(String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("User is not authenticated.");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof Users)) {
            throw new BadRequestException("Invalid user principal.");
        }

        Users currentUser = (Users) principal;

        if (currentUser.getId() == null) {
            throw new BadRequestException("User ID is missing.");
        }

        if (currentUser.getBuiltIn()) {
            throw new BadRequestException("Cannot delete built-in user.");
        }

        if (!passwordEncoder.matches(password, currentUser.getPassword_hash())) {
            throw new BadRequestException("Invalid password.");
        }

        boolean hasAdverts = advertsService.userHasAdverts(currentUser.getId());
        boolean hasTourRequests = tourRequestsService.userHasTourRequests(currentUser.getId());

        if (hasAdverts || hasTourRequests) {
            throw new BadRequestException("User has related records and cannot be deleted.");
        }

        favoritesService.deleteByUserId(currentUser.getId());
        logsService.deleteByUserId(currentUser.getId());

        userRepository.deleteById(currentUser.getId());
    }


    //******************************************************************************************

    public Page<UserGetPageableResponse> getAdminUsers(String q, Pageable pageable) {
        if (q != null && !q.trim().isEmpty()) {
            // Arama yaparak sayfalama işlemini gerçekleştirir
            return userRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContaining(
                            q, q, q, q, pageable)
                    .map(userMapper::mapUserToUserGetPageableResponse);
        } else {
            // Arama yapılmadan sayfalama işlemini gerçekleştirir
            return userRepository.findAll(pageable)
                    .map(userMapper::mapUserToUserGetPageableResponse);
        }
    }



//******************************************************************************************


    public UserGetIdResponse getUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.mapUserToUserGetIdResponse(user);
    }

//******************************************************************************************




    public UserUpdateWithIdResponse updateUser(UserUpdateWithIdRequest updateRequest, Authentication authentication) {
        // Kullanıcının e-posta adresini alıyoruz.
        String email = authentication.getName();

        // Kimlik bilgisi ile kullanıcıyı alıyoruz.
        Users currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Kullanıcıyı güncelliyoruz.
        if (currentUser.getBuiltIn()) {
            throw new IllegalStateException("Built-in users cannot be updated.");
        }

        Users updatedUser = userMapper.mapUserUpdateWithIdRequestToUser(updateRequest, currentUser);
        userRepository.save(updatedUser);

        return userMapper.mapUserToUserUpdateWithIdResponse(updatedUser);
    }


    //******************************************************************************************



    public UserDeleteResponse deleteUserById(Long id, Authentication authentication) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Kullanıcının built-in olup olmadığını kontrol et
        if (Boolean.TRUE.equals(user.getBuiltIn())) {
            throw new AccessDeniedException("Built-in user cannot be deleted.");
        }

        // Kullanıcının ilanları veya tur talepleri olup olmadığını kontrol et
        if (advertsService.userHasAdverts(id) ||
            tourRequestsService.userHasTourRequests(id)) {
            throw new AccessDeniedException("User with related adverts or tour requests cannot be deleted.");
        }

        // Favoriler ve log kayıtlarını sil
        favoritesService.deleteByUserId(id);
        logsService.deleteByUserId(id);

        // Kullanıcıyı sil
        userRepository.delete(user);

        // Yanıt döndür
        return userMapper.toUserDeleteResponse(user);
    }


}