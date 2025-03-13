package cat.itacademy.s05.t02.Controllers;

import cat.itacademy.s05.t02.DTOs.User.CreateUserDTO;
import cat.itacademy.s05.t02.Models.User;
import cat.itacademy.s05.t02.Service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private static final Logger logger = LogManager.getLogger(UserAuthController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    public Mono<String> registerUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.findByUsername(createUserDTO.getUsername())
                .flatMap(existingUser -> {
                    logger.info("Username already exists: " + createUserDTO.getUsername());
                    return Mono.just("Username already exists");
                })
                .switchIfEmpty(userService.registerUser(new User(createUserDTO.getName(), createUserDTO.getUsername(), createUserDTO.getEmail(), createUserDTO.getPassword(), createUserDTO.getRoleType()))
                        .thenReturn("User registered successfully"));
    }

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Login failed")
    })
    public Mono<String> loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password)
                .doOnSuccess(result -> {
                    if (result) {
                        logger.info("Login successful for user: " + username);
                    } else {
                        logger.info("Login failed for user: " + username);
                    }
                })
                .map(result -> result ? "Login successful" : "Login failed");
    }
}