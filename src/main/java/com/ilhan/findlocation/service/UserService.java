package com.ilhan.findlocation.service;

import com.ilhan.findlocation.dto.UserDtoRequest;
import com.ilhan.findlocation.dto.UserDtoResponse;
import com.ilhan.findlocation.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findById(Long id);
    User save(UserDtoRequest user) throws Exception;
    UserDetails loadUserByUsername(String username);
    Optional<User> findByUsername(String username);
    void deleteAll();
    List<User> findAll();
}
