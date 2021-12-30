package be.tirza.dinningreviewapi.controller;

import be.tirza.dinningreviewapi.entity.Role;
import be.tirza.dinningreviewapi.entity.User;
import be.tirza.dinningreviewapi.payload.JwtAuthResponse;
import be.tirza.dinningreviewapi.payload.LoginDTO;
import be.tirza.dinningreviewapi.payload.SignUpDTO;
import be.tirza.dinningreviewapi.repository.RoleRepository;
import be.tirza.dinningreviewapi.repository.UserRepository;
import be.tirza.dinningreviewapi.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(value = "Auth controller for signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation (value = "REST API to signIn to dinning review app")
    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(),
                loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @ApiOperation (value = "REST API to register to dinning review app")
    @PostMapping("/signUp")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDTO signUpDTO) {

        //check for username if already exist in DB
        if(userRepository.existsByUserName(signUpDTO.getUserName())){
            return new ResponseEntity<>("User name is already taken. Please provide another user name. ", HttpStatus.BAD_REQUEST);
        }

        //check for email if already exist in DB
        if(userRepository.existsByEmail(signUpDTO.getEmail())){
            return new ResponseEntity<>("Email is already exist. Please provide another email address", HttpStatus.BAD_REQUEST);
        }

        //create user object
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setUserName(signUpDTO.getUserName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
