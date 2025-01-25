package com.matheus.desafio.security;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.matheus.desafio.entity.Usuario;
public class UserPrincipal implements UserDetails  {

    
    
	
	private String senha;
	private String usuario;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserPrincipal(Usuario usuario) {
		this.senha = usuario.getSenha();
		this.usuario = usuario.getUsuario();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	
	}@Override
	public String getUsername() {
		return this.usuario;
	
	}@Override
	public boolean isAccountNonExpired() {
		return true;
	
	}@Override
	public boolean isAccountNonLocked() {
		return true;
	
	}@Override
	public boolean isCredentialsNonExpired() {
		return true;
	
	}@Override
	public boolean isEnabled() {
		return true;
	}


}
