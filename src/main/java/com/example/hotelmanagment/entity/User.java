package com.example.hotelmanagment.entity;

import com.example.hotelmanagment.enumeration.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity implements Serializable, UserDetails {

    private String firstName;
    private String lastName;

    @Email(message = "Email noto'g'ri formatda")
    private String email;

    @Size(min = 5, max = 16, message = "Password 5 va 16 uzunlik orasida bo'lishi kerak")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private List<UserRoles> roles;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> order = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Payment> payment = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
        return false;
    }
}
