package com.mmplus.promo.service;

import com.mmplus.promo.repository.UserRepository;
import com.mmplus.promo.domain.profiles.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserRepositoryUserDetailsService")
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        }
        else throw  new UsernameNotFoundException("" +
                "User '" +username +"' not found") ;
    }
}
