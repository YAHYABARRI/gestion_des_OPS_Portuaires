package com.example.springbootstg2.service;

import com.example.springbootstg2.models.AgentMaritime;
import com.example.springbootstg2.repository.AgentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AgentRepository AgentRepository;

    public UserDetailsServiceImpl(AgentRepository AgentRepository) {
        this.AgentRepository = AgentRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AgentMaritime agent = (AgentMaritime) AgentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(agent);
    }
}