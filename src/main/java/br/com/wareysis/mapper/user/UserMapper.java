package br.com.wareysis.mapper.user;

import java.time.LocalDateTime;
import java.util.Objects;

import com.google.firebase.auth.UserRecord;

import br.com.wareysis.domain.user.User;
import br.com.wareysis.dto.user.UpdateUserDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User toEntity(UserRecord userRecord) {

        User user = new User();

        user.setEmail(userRecord.getEmail());
        user.setFullName(userRecord.getDisplayName());
        user.setEmailVerified(userRecord.isEmailVerified());
        user.setPhoneNumber(userRecord.getPhoneNumber());
        user.setPhotoUrl(userRecord.getPhotoUrl());
        user.setDisabled(userRecord.isDisabled());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setFirebaseUid(userRecord.getUid());
        user.setChangePassword(false);

        return user;
    }

    public void updateFromDto(User user, UpdateUserDto updateUserDto) {

        if (Objects.nonNull(updateUserDto.email()) && !updateUserDto.email().isBlank()) {
            user.setEmail(updateUserDto.email());
        }
        if (Objects.nonNull(updateUserDto.fullName()) && !updateUserDto.fullName().isBlank()) {
            user.setFullName(updateUserDto.fullName());
        }
        if (Objects.nonNull(updateUserDto.phoneNumber()) && !updateUserDto.phoneNumber().isBlank()) {
            user.setPhoneNumber(updateUserDto.phoneNumber());
        }
        if (Objects.nonNull(updateUserDto.photoUrl()) && !updateUserDto.photoUrl().isBlank()) {
            user.setPhotoUrl(updateUserDto.photoUrl());
        }
        if (Objects.nonNull(updateUserDto.disabled())) {
            user.setDisabled(updateUserDto.disabled());
        }
        if (Objects.nonNull(updateUserDto.changePassword())) {
            user.setChangePassword(updateUserDto.changePassword());
        }

        user.setUpdateTime(LocalDateTime.now());
    }

}
