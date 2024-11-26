package ru.readzero.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.readzero.entity.absctract.BaseEntity;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "USERS_SEQ")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "USERNAME", unique = true, nullable = false)
    String username;

    @Column(name = "EMAIL", unique = true, nullable = false)
    String email;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Column(name = "IS_BLOCKED", nullable = false)
    boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    UserRole role;

    @OneToOne(mappedBy = "user")
    UserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked;
    }
}
