package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.Favorite;
import com.team02.best_properta.entity.concretes.business.Log;
import com.team02.best_properta.entity.concretes.business.TourRequest;
import com.team02.best_properta.entity.concretes.user.Users;
import com.team02.best_properta.payload.request.abstracts.BaseUserRequest;
import com.team02.best_properta.payload.request.user.UserPasswordUpdateRequest;
import com.team02.best_properta.payload.request.user.UserUpdateRequest;
import com.team02.best_properta.payload.request.user.UserUpdateWithIdRequest;
import com.team02.best_properta.payload.response.business.AdvertsGetIdResponse;
import com.team02.best_properta.payload.response.business.FavoritesGetIdResponse;
import com.team02.best_properta.payload.response.business.LogsGetIdResponse;
import com.team02.best_properta.payload.response.business.TourRequestsGetIdResponse;
import com.team02.best_properta.payload.response.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component // Bu sınıfın bir Spring bileşeni olduğunu belirtir.
public class UsersMapper {



    private final PasswordEncoder passwordEncoder;



    public Users mapUserRequestToUser(BaseUserRequest userRequest){

        return Users.builder()

                .firstName(userRequest.getFirstName()) // Adı ayarlıyoruz.
                .lastName(userRequest.getLastName())   // Soyadı ayarlıyoruz.
                .email(userRequest.getEmail())         // E-posta adresini ayarlıyoruz.
                .phone(userRequest.getPhone())         // Telefon numarasını ayarlıyoruz.
                .createAt(LocalDateTime.now()) // Kullanıcının oluşturulma zamanını ayarlıyoruz.
                .builtIn(userRequest.getBuiltIn())// Kullanıcının built-in olup olmadığını false olarak ayarlıyoruz.
                .password_hash(userRequest.getPassword_hash())
                .build(); // Kullanıcı nesnesini oluşturuyoruz.
    }




    public UserResponse mapUserToUserResponse(Users user){
        return  UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getEmail())
                .phone(user.getPhone())
                .build();

    }



    // Yeni metod: Users nesnesini AuthenticatedUserResponse'a dönüştürür
    public AuthenticatedUserResponse mapUserToAuthenticatedUserResponse(Users user) {
        return AuthenticatedUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }



    public Users mapUserUpdateRequestToUser(UserUpdateRequest updateRequest, Users currentUser) {
        if (updateRequest.getFirstName() != null) {
            currentUser.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            currentUser.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getPhone() != null) {
            currentUser.setPhone(updateRequest.getPhone());
        }
        return currentUser;
    }

    public UserUpdateResponse mapUserToUserUpdateResponse(Users user) {
        return UserUpdateResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }




    public Users mapUserPasswordUpdateRequestToUser(UserPasswordUpdateRequest updateRequest, Users currentUser) {
        if (updateRequest.getPassword() != null) {
            currentUser.setPassword_hash(passwordEncoder.encode(updateRequest.getPassword()));
        }
        return currentUser;
    }

    public UserPasswordUpdateResponse mapUserToUserPasswordUpdateResponse(Users user) {
        return UserPasswordUpdateResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }




    public UserGetPageableResponse mapUserToUserGetPageableResponse(Users user) {
        return UserGetPageableResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }




    // Convert User entity to UserGetIdResponse DTO
    public UserGetIdResponse mapUserToUserGetIdResponse(Users user) {
        return UserGetIdResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .adverts(mapAdverts(user.getAdverts()))
                .tourRequests(mapTourRequests(user.getGuestTourRequests()))
                .favorites(mapFavorites(user.getFavorites()))
                .logs(mapLogs(user.getLogs()))
                .build();
    }

    // Mapping methods for related entities
    private List<AdvertsGetIdResponse> mapAdverts(List<Advert> adverts) {
        return adverts.stream()
                .map(advert -> new
                        AdvertsGetIdResponse
                        (advert.getId(),
                        advert.getTitle(),
                        advert.getDescription()))

                .collect(Collectors.toList());
    }

    private List<TourRequestsGetIdResponse> mapTourRequests(List<TourRequest> tourRequests) {
        return tourRequests.stream()
                .map(tourRequest ->
                        new TourRequestsGetIdResponse(
                                tourRequest.getId(),
                                tourRequest.getStatus(),
                                tourRequest.getTourDate()))
                .collect(Collectors.toList());
    }

    private List<FavoritesGetIdResponse> mapFavorites(List<Favorite> favorites) {
        return favorites.stream()
                .map(favorite ->
                        new FavoritesGetIdResponse(
                                favorite.getId(),
                                favorite.getCreateAt()))
                .collect(Collectors.toList());
    }

    private List<LogsGetIdResponse> mapLogs(List<Log> logs) {
        return logs.stream()
                .map(log ->
                        new LogsGetIdResponse(
                                log.getId(),
                                log.getLog(),
                                log.getCreateAt()))
                .collect(Collectors.toList());
    }



    public Users mapUserUpdateWithIdRequestToUser(UserUpdateWithIdRequest updateRequest, Users currentUser) {
        if (updateRequest.getFirstName() != null) {
            currentUser.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            currentUser.setLastName(updateRequest.getLastName());
        }
        return currentUser;
    }

    public UserUpdateWithIdResponse mapUserToUserUpdateWithIdResponse(Users user) {
        return UserUpdateWithIdResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }



    public UserDeleteResponse toUserDeleteResponse(Users user) {
        return UserDeleteResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }







    









}
