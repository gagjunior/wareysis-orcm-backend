package br.com.wareysis.dto.user;

public record UpdateUserDto(

        String email,
        String fullName,
        String phoneNumber,
        String photoUrl,
        Boolean disabled,
        Boolean changePassword

) {

}
