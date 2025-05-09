package br.com.wareysis.domain.user;

import java.io.Serializable;

import br.com.wareysis.core.domain.AbstractDomainBase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_registration")
public class User extends AbstractDomainBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "photo_url")
    private String photoUrl;

    private Boolean disabled;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    @Column(name = "change_password")
    private Boolean changePassword;

}
