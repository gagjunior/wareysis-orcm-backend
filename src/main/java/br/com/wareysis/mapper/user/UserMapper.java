package br.com.wareysis.mapper.user;

import java.util.Objects;
import java.util.Optional;

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
        user.setFirebaseUid(userRecord.getUid());
        user.setChangePassword(false);

        return user;
    }

    public void updateFromDto(User user, UpdateUserDto updateUserDto) {

        if (isValid(updateUserDto.email())) {
            user.setEmail(updateUserDto.email());
        }
        if (isValid(updateUserDto.fullName())) {
            user.setFullName(updateUserDto.fullName());
        }
        if (isValid(updateUserDto.phoneNumber())) {
            user.setPhoneNumber(updateUserDto.phoneNumber());
        }
        if (isValid(updateUserDto.photoUrl())) {
            user.setPhotoUrl(updateUserDto.photoUrl());
        }
        if (Objects.nonNull(updateUserDto.changePassword())) {
            user.setChangePassword(updateUserDto.changePassword());
        }

        Optional.ofNullable(updateUserDto.changePassword()).ifPresent(user::setChangePassword);
        Optional.ofNullable(updateUserDto.disabled()).ifPresent(user::setDisabled);

    }

    private boolean isValid(String value) {

        return Objects.nonNull(value) && !value.isBlank();
    }

}
