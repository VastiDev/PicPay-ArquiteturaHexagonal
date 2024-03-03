package com.example.picpay_hexagonal.user.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public interface UserAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    UserResponse postUser(@RequestBody UserRequest userRequest);

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    UserDetailedResponse getUserById (@PathVariable Long id);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<UserListResponse> getAllUsers();
}
