package com.spring.gucms.controller;

import com.spring.gucms.dto.JwtLogin;
import com.spring.gucms.dto.JwtResponse;
import com.spring.gucms.entity.User;
import com.spring.gucms.repository.UserRepository;
import com.spring.gucms.service.AuthoritiesService;
import com.spring.gucms.service.TokenService;
import com.spring.gucms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    private TokenService tokenService;
    private PasswordEncoder password;
    private UserService userService;
    private AuthoritiesService authoritiesService;
    private UserRepository userRepository;
    @Autowired
    public UserController(TokenService tokenService, PasswordEncoder password, UserService userService, AuthoritiesService authoritiesService,UserRepository userRepository) {
        this.tokenService = tokenService;
        this.password = password;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
        this.userRepository = userRepository;
    }
    //api/v1/user/signIn
    @PostMapping("signIn")
    public JwtResponse login(@RequestBody JwtLogin jwtLogin){
        return tokenService.login(jwtLogin);
    }
    @PostMapping("signUp")
    public void signUp(@RequestBody JwtLogin jwtLogin){
        System.out.println("jjjjj");
        System.out.println(jwtLogin.getUserName());
        User userData = new User();
        userData.setEmail(jwtLogin.getUserName());
        userData.setPassword(password.encode(jwtLogin.getPassword()));
        userData.setActive(true);
        userData.getAuthorities().add(authoritiesService.getAllAuthorities().get(0));
        userService.signUp(userData);
    }
    @PostMapping("setting/{email}")
    public void setting(@RequestBody JwtLogin jwtLogin,@PathVariable String email){
        System.out.println("jjjjj");
        System.out.println(jwtLogin.getUserName());
        //UserDetails user =  userService.loadUserByUsername(jwtLogin.getUserName());
        User userData = userRepository.findByEmail(email);
        System.out.println(userData.getId());
        //System.out.println(userData.getEmail());
        userData.setEmail(jwtLogin.getUserName());
        //userData.setPassword(password.encode(userData.getPassword()));
        userData.setActive(true);
        userData.getAuthorities().add(authoritiesService.getAllAuthorities().get(0));
        userData.setPassword("");
        userData.setPassword(password.encode(jwtLogin.getPassword()));
        System.out.println(userData.getEmail());
        System.out.println(userData.getPassword());
        userService.signUp(userData);
    }
}
