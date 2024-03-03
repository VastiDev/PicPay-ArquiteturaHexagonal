package com.example.picpay_hexagonal.user.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public interface UserAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    UserResponse postUser(@RequestBody UserRequest userRequest);
}
