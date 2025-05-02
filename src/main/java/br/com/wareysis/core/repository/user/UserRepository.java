package br.com.wareysis.core.repository.user;

import java.util.Map;

import br.com.wareysis.domain.user.User;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    public User findUserByFirebaseUid(String firebaseUid) {

        return find("firebaseUid = :firebaseUid", Map.of("firebaseUid", firebaseUid)).singleResult();
    }

}
