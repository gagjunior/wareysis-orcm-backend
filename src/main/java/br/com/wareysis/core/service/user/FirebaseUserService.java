package br.com.wareysis.core.service.user;

import java.util.Objects;

import org.jboss.logging.Logger;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.dto.user.CreateUserDto;
import br.com.wareysis.dto.user.UpdateUserDto;
import br.com.wareysis.exception.user.UserException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class FirebaseUserService extends AbstractService {

    @Inject
    Logger log;

    public UserRecord createUserInFirebase(CreateUserDto createUserDto) {

        log.info(String.format("OrcmApp - FIREBASE: Salvar usuário com email: %s", createUserDto.email()));

        if (userAlreadyExistsInFirbase(createUserDto.email())) {
            throw new UserException(messageService.getMessage("user.firebase.already.exists", createUserDto.email()), Response.Status.BAD_REQUEST);
        }

        try {

            return FirebaseAuth.getInstance().createUser(createRequestFirebase(createUserDto));

        } catch (Exception e) {

            throw new UserException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    public void updateUserInFirebase(UpdateUserDto updateUserDto, String firebaseUid) {

        log.info(String.format("OrcmApp - FIREBASE: Alterar usuário com uid: %s", firebaseUid));

        try {

            UserRecord userRecord = FirebaseAuth.getInstance().getUser(firebaseUid);

            if (Objects.isNull(userRecord)) {
                throw new UserException(messageService.getMessage("user.firebase.notFound", firebaseUid), Response.Status.INTERNAL_SERVER_ERROR);
            }

            FirebaseAuth.getInstance().updateUser(updateRequestFirebase(updateUserDto, firebaseUid));

        } catch (FirebaseAuthException e) {

            throw new UserException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    public FirebaseToken getFirebaseToken(ContainerRequestContext ctx) {

        return (FirebaseToken) ctx.getProperty("firebaseUser");

    }

    private UserRecord.UpdateRequest updateRequestFirebase(UpdateUserDto updateUserDto, String firebaseUid) {

        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(firebaseUid);

        if (Objects.nonNull(updateUserDto.email()) && !updateUserDto.email().isBlank()) {
            request.setEmail(updateUserDto.email());
        }

        if (Objects.nonNull(updateUserDto.fullName()) && !updateUserDto.fullName().isBlank()) {
            request.setDisplayName(updateUserDto.fullName());
        }

        if (Objects.nonNull(updateUserDto.phoneNumber()) && !updateUserDto.phoneNumber().isBlank()) {
            request.setPhoneNumber(updateUserDto.phoneNumber());
        }

        if (Objects.nonNull(updateUserDto.photoUrl()) && !updateUserDto.photoUrl().isBlank()) {
            request.setPhotoUrl(updateUserDto.photoUrl());
        }

        if (Objects.nonNull(updateUserDto.disabled())) {
            request.setDisabled(updateUserDto.disabled());
        }

        return request;

    }

    private UserRecord.CreateRequest createRequestFirebase(CreateUserDto createUserDto) {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest();

        request.setEmail(createUserDto.email());
        request.setPassword(createUserDto.password());
        request.setDisplayName(createUserDto.fullName());
        request.setDisabled(false);
        request.setEmailVerified(false);

        return request;
    }

    private boolean userAlreadyExistsInFirbase(String email) {

        try {

            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            return (Objects.nonNull(userRecord) && userRecord.getEmail().equals(email));

        } catch (FirebaseAuthException e) {

            if (e.getAuthErrorCode().equals(AuthErrorCode.EMAIL_NOT_FOUND) || e.getAuthErrorCode().equals(AuthErrorCode.USER_NOT_FOUND)) {
                return false;
            } else {
                throw new UserException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
            }

        }

    }

}
