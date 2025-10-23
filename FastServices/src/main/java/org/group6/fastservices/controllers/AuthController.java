package org.group6.fastservices.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

//    private final AuthenticationManager authenticationManager;
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public AuthController(AuthenticationManager authenticationManager,
//                          UserService userService,
//                          PasswordEncoder passwordEncoder,
//                          JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Login loginDto) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginDto.getEmail(),
//                            loginDto.getPassword()
//                    )
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtTokenProvider.generateToken(authentication);
//
//            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//            jwtAuthResponse.setAccessToken(token);
//            jwtAuthResponse.setTokenType("Bearer");
//
//            return ResponseEntity.ok(jwtAuthResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterUserRequest userRequest) {
//        try {
//            if (userService.existsByEmail(userRequest.getEmail())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ErrorResponse<>("Email already exists"));
//            }
//
//            User user = new User();
//            BeanUtils.copyProperties(userRequest, user);
//            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//
//            Set<org.group6.fastservices.data.models.Role> roles = new HashSet<>();
//            roles.add(org.group6.fastservices.data.models.Role.CUSTOMER);
//            user.setRoles(roles);
//
//            User savedUser = userService.createUser(user);
//
//            UserResponse userResponse = Mapper.mapToUserResponse(savedUser);
//            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ErrorResponse<>(e.getMessage()));
//        }
//    }
}