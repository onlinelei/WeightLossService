package top.okeng.controller;

import top.okeng.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.okeng.auth.security.JwtUtil;
import top.okeng.dto.AuthRequest;
import top.okeng.dto.AuthResponse;
import top.okeng.dto.UserRegistrationDto;
import top.okeng.rpc.response.RPCBaseResult;
import top.okeng.rpc.template.SOAProviderTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends SOAProviderTemplate {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public RPCBaseResult<?> register(@RequestBody UserRegistrationDto registrationDto) {
        return execute(() -> {
            return userService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
        }, SOAProviderTemplate::getFail);

    }

    @PostMapping("/login")
    public RPCBaseResult<?> login(@RequestBody AuthRequest authRequest) {
        return execute(() -> {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return new AuthResponse(jwt, authRequest.getUsername());
        }, SOAProviderTemplate::getFail);
    }
}