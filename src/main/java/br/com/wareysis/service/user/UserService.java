package br.com.wareysis.service.user;

import java.util.Objects;

import com.google.firebase.auth.UserRecord;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.user.User;
import br.com.wareysis.dto.user.CreateUserDto;
import br.com.wareysis.dto.user.UpdateUserDto;
import br.com.wareysis.exception.user.UserException;
import br.com.wareysis.mapper.user.UserMapper;
import br.com.wareysis.service.firebase.user.FirebaseUserService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class UserService extends AbstractService {

    @Inject
    FirebaseUserService firebaseUserService;

    @Inject
    UserMapper userMapper;

    public User createUser(CreateUserDto createUserDto) {

        if (Objects.isNull(createUserDto)) {
            throw new UserException(messageService.getMessage("user.create.dto.null"), Response.Status.BAD_REQUEST);
        }

        UserRecord userRecord = firebaseUserService.createUserInFirebase(createUserDto);

        User user = userMapper.toEntity(userRecord);

        user.persist();

        return user;
    }

    public User updateUser(Long userId, UpdateUserDto updateUserDto) {

        if (userId == 1L) {
            throw new UserException(messageService.getMessage("user.update.admin.not.allowed"), Status.BAD_REQUEST);
        }

        if (Objects.isNull(updateUserDto) || userId <= 0) {
            throw new UserException(messageService.getMessage("user.update.dto.null"), Response.Status.BAD_REQUEST);
        }

        User user = User.findById(userId);

        if (Objects.isNull(user)) {
            throw new UserException(messageService.getMessage("user.not.found"), Response.Status.NOT_FOUND);
        }

        firebaseUserService.updateUserInFirebase(updateUserDto, user.getFirebaseUid());
        userMapper.updateFromDto(user, updateUserDto);

        return user;

    }

}
