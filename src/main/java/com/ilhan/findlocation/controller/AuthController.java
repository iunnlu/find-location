package com.ilhan.findlocation.controller;

import com.ilhan.findlocation.dto.AuthRequestDto;
import com.ilhan.findlocation.service.UserService;
import com.ilhan.findlocation.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping
    public String auth(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        authenticate(authRequestDto.getUsername(), authRequestDto.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authRequestDto.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return token;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
