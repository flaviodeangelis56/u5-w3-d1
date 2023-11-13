package flaviodeangeelis.u5w2d5.controller;

import flaviodeangeelis.u5w2d5.entities.User;
import flaviodeangeelis.u5w2d5.exception.BadRequestException;
import flaviodeangeelis.u5w2d5.payload.LogInSuccesUserDTO;
import flaviodeangeelis.u5w2d5.payload.LogInUserDTO;
import flaviodeangeelis.u5w2d5.payload.NewUserDTO;
import flaviodeangeelis.u5w2d5.service.AuthService;
import flaviodeangeelis.u5w2d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService usersService;
    @Autowired
    private AuthService authService;

    @PutMapping("/login")
    @PostMapping("/login")
    public LogInSuccesUserDTO login(@RequestBody LogInUserDTO body) {

        return new LogInSuccesUserDTO(authService.userAuthenticate(body));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return usersService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
