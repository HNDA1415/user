package com.example.usertest.security;

import com.example.usertest.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal implements UserDetails {


    private static final long serialVersionUID = 8815539804878290571L;

    private int id;

    private String userName;
    private String mobileNumber;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int id,String userName, String mobileNumber, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName=userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal build(UserEntity user){
        List<GrantedAuthority> grantedAuthorities=user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
                return new UserPrincipal(user.getId(), user.getUserName(), user.getMobileNumber(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getMobileNumber(){
        return mobileNumber;
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
