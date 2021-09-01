package com.pwc.assignment.controller;

import com.pwc.assignment.authentication.JwtTokenProvider;
import com.pwc.assignment.model.SystemUser;
import com.pwc.assignment.model.authentication.JwtResponse;
import com.pwc.assignment.model.authentication.Login;
import com.pwc.assignment.service.UserService;
import com.pwc.assignment.service.response.Response;
import com.pwc.assignment.service.response.error.exception.client.BadRequestException;
import com.pwc.assignment.service.response.success.OkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response createAuthenticationToken(@RequestBody Login loginRequest) {

        Authentication authentication;
        String token;
        String user;
        SystemUser systemUser;

        if (userService.isUserExists(loginRequest.getUsername())) {
            authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            user = userDetails.getUsername();
            token = jwtTokenProvider.generateToken(authentication);
            systemUser = userService.getUserByUserName(user);
            systemUser.setPassword(null);
        } else {
            throw new BadRequestException(String.format("Can not generate token. User is inactive.!",
                    loginRequest.getUsername()));
        }

        JwtResponse response = new JwtResponse(token, user, systemUser);

        return new OkResponse("Token generated successfully", response);
    }

    private Authentication authenticate(String username, String password) {

        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid credentials!");
        }
    }

}
