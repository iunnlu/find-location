package com.ilhan.findlocation.controller;

import com.ilhan.findlocation.dto.UserDtoRequest;
import com.ilhan.findlocation.dto.UserDtoResponse;
import com.ilhan.findlocation.entity.User;
import com.ilhan.findlocation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDtoResponse findById(@PathVariable Long id) throws Exception {
        User user = userService.findById(id).orElseThrow(() -> new Exception("User not found"));
        return modelMapper.map(user, UserDtoResponse.class);
    }

    @PostMapping("/create")
    public UserDtoResponse save(@RequestBody UserDtoRequest userDto) {
        return modelMapper.map(userService.save(userDto), UserDtoResponse.class);
    }
}
