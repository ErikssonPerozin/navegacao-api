package br.com.faculdade.navegacaoapi.model;

import jakarta.persistence.*;
import lombok.Data;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Usuario implements UserDetails { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String tipo; 

    @Column(nullable = false)
    private String senha;

    
    

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo == null) {
            
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.tipo.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return this.senha; 
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
        return true; 
    }
}