package com.example.client.service;


import com.example.client.entity.ClientEntity;
import com.example.client.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientAuthService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientEntity> clientEntityOptional = clientRepository.findByEmail(username);
        if (clientEntityOptional.isEmpty()) throw new UsernameNotFoundException("Client with this email not found");
        ClientEntity client= clientEntityOptional.get();
        List<SimpleGrantedAuthority> authorities=List.of(new SimpleGrantedAuthority("user"));
        return new User(client.getEmail(),client.getPassword(),authorities);
    }
}
