package com.yandiar.absensi.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yandiar.absensi.jwt.JwtUtils;
import com.yandiar.absensi.model.entity.ERole;
import com.yandiar.absensi.model.entity.Role;
import com.yandiar.absensi.model.entity.User;
import com.yandiar.absensi.model.request.LoginRequest;
import com.yandiar.absensi.model.request.SignupRequest;
import com.yandiar.absensi.model.response.ApiResponse;
import com.yandiar.absensi.model.response.AuthResponse;
import com.yandiar.absensi.repository.RoleRepository;
import com.yandiar.absensi.repository.UserRepository;
import com.yandiar.absensi.services.UserDetailsImpl;

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

/**
 * 
 * @author YAR
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepo;

  @Autowired
  RoleRepository roleRepo;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity
        .ok(new AuthResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (userRepo.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new ApiResponse(HttpStatus.BAD_REQUEST, "Username is already taken."));
    }

    if (userRepo.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new ApiResponse(HttpStatus.BAD_REQUEST, "Email is already taken."));
    }

    User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),
        encoder.encode(signupRequest.getPassword()));

    Set<String> strRoles = signupRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepo.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);    
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
          roles.add(adminRole);
          break;
        default:
          Role userRole = roleRepo.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepo.save(user);

    return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "User registered successfully."));
  }
}
