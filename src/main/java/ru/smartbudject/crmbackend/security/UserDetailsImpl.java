package ru.smartbudject.crmbackend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.smartbudject.crmbackend.model.entity.Account;

import java.util.Collection;
import java.util.Collections;


/**
 * Реализация для использования секрюрити.
 * @param user
 */
public record UserDetailsImpl(Account user) implements UserDetails {

    /**
     * Метод для получения прав пользователя.
     *
     * @return права
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    /**
     * Метод для получения пароля пользователя.
     *
     * @return пароль
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Метод для получения username пользователя.
     *
     * @return username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
