package top.okeng.controller;

import top.okeng.dto.ChangePasswordRequest;
import top.okeng.dto.TokenResponse;
import top.okeng.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import top.okeng.auth.security.JwtUtil;
import top.okeng.dto.AuthRequest;
import top.okeng.dto.AuthResponse;
import top.okeng.dto.UserRegistrationDto;
import top.okeng.entity.User;
import top.okeng.rpc.response.RPCBaseResult;
import top.okeng.rpc.template.SOAProviderTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends SOAProviderTemplate {
    private static final Long EXPIRES_IN = 31536000L; // 1年

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
            User user = userService.registerUser(registrationDto.getUsername(),
                    registrationDto.getPassword(), registrationDto.getConfirmPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            Map<String, Object> result = new HashMap<>();
            result.put("userId", String.valueOf(user.getId()));
            result.put("username", user.getUsername());
            result.put("token", jwt);
            result.put("expiresIn", EXPIRES_IN);
            return result;
        }, SOAProviderTemplate::getFail);
    }

    @PostMapping("/login")
    public RPCBaseResult<?> login(@RequestBody AuthRequest authRequest) {
        return execute(() -> {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            User user = userService.findByUsername(authRequest.getUsername());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            Map<String, Object> result = new HashMap<>();
            result.put("userId", String.valueOf(user.getId()));
            result.put("username", user.getUsername());
            result.put("token", jwt);
            result.put("expiresIn", EXPIRES_IN);
            return result;
        }, SOAProviderTemplate::getFail);
    }

    @PostMapping("/refresh")
    public RPCBaseResult<?> refresh(@AuthenticationPrincipal UserDetails userDetails) {
        return execute(() -> {
            final String jwt = jwtUtil.generateToken(userDetails);
            return new TokenResponse(jwt, EXPIRES_IN);
        }, SOAProviderTemplate::getFail);
    }

    @PostMapping("/logout")
    public RPCBaseResult<?> logout(@AuthenticationPrincipal UserDetails userDetails) {
        return executeWithoutResult(() -> {
            // JWT 是无状态的，客户端删除 token 即可
            // 如需服务端失效，可以实现 token 黑名单机制
            return null;
        }, SOAProviderTemplate::getFail);
    }

    @PutMapping("/password")
    public RPCBaseResult<?> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody ChangePasswordRequest request) {
        return executeWithoutResult(() -> {
            userService.changePassword(userDetails.getUsername(),
                    request.getOldPassword(), request.getNewPassword());
            return null;
        }, SOAProviderTemplate::getFail);
    }
}