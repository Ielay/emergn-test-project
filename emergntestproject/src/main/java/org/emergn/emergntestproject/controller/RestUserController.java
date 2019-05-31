package org.emergn.emergntestproject.controller;

import org.emergn.emergntestproject.domain.User;
import org.emergn.emergntestproject.dto.LoginFormDTO;
import org.emergn.emergntestproject.dto.RegistrationFormDTO;
import org.emergn.emergntestproject.dto.UsersTableDTO;
import org.emergn.emergntestproject.security.jwt.JwtProvider;
import org.emergn.emergntestproject.security.jwt.JwtResponse;
import org.emergn.emergntestproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class RestUserController implements UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    private UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody LoginFormDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.login, loginRequest.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @Override
    @GetMapping(path = "/users")
    public ResponseEntity<List<UsersTableDTO>> usersTable() {
        List<UsersTableDTO> users = userService.getUsersForUsersTable();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody RegistrationFormDTO registrationForm) {
        boolean userWasAdded = userService.saveUser(registrationForm);

        if (!userWasAdded) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/edit")
    public ResponseEntity editUser(@RequestBody User editedUser) {
        boolean userWasUpdated = userService.updateUser(editedUser);

        if (!userWasUpdated) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
