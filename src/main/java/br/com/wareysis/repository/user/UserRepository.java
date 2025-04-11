package br.com.wareysis.repository.user;

import br.com.wareysis.domain.user.User;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Long> {

}
