package app.library.security;

import app.library.model.UserTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserTablePrincipal implements UserDetails {

    private UserTable userTable;

    public UserTablePrincipal(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("users"));
    }

    @Override
    public String getPassword() {
        return userTable.getPassword();
    }

    @Override
    public String getUsername() {
        return userTable.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
