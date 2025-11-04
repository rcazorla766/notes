package es.notes.notes.service;

import es.notes.notes.model.AppUser;
import es.notes.notes.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repo;

    @Autowired
    public CustomUserDetailsService(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(new SimpleGrantedAuthority(appUser.getRole()))
                .build();
    }
}
