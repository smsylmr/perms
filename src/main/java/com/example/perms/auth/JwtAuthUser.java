package com.example.perms.auth;

import com.example.perms.bean.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author linmr
 * @description:
 * @date 2020/12/10
 */
public class JwtAuthUser implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * description: 通过FXUser来创建JwtAuthUser
     *
     * @param user
     * @return
     */
    public JwtAuthUser(SysUser user){
        this.id=user.getUserId();
        this.userName=user.getLoginName();
        this.password=user.getPassword();
        this.enabled= user.getStatus().equals("0");
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())) ;
    }

    /**
     * description: 鉴权最重要方法，通过此方法来返回用户权限
     *
     * @param
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
        return this.enabled;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "JwtAuthUser{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
