package flaviodeangeelis.u5w2d5.service;

import flaviodeangeelis.u5w2d5.entities.User;
import flaviodeangeelis.u5w2d5.exception.UnauthorizedException;
import flaviodeangeelis.u5w2d5.payload.LogInUserDTO;
import flaviodeangeelis.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public String userAuthenticate(LogInUserDTO body) {
        User user = userService.findByEmail(body.email());

        if (body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
