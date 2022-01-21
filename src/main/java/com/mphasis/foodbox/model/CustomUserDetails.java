package com.mphasis.foodbox.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
* CustomUserDetails object to interface with spring security framework
*
*/
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -4095994931010952641L;
	private String name;
    private String email;
    private String password;
	private String address;
	private List<SimpleGrantedAuthority> authorities;
	private boolean active;
     
    public CustomUserDetails(User user) {
    	this.email = user.getEmail();
    	this.password = user.getPassword();
    	this.name = user.getName();
    	this.address = user.getAddress();
    	this.active = user.isActive();
    	this.authorities = Arrays.stream(user.getRoles().split(","))
    			.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
    }
 
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
        return this.email;
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
        return this.active;
    }
     
    public String getName() {
        return this.name;
    }
}
