package com.yandiar.absensi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.yandiar.absensi.jwt.JwtUtils;
import com.yandiar.absensi.model.request.LoginRequest;
import com.yandiar.absensi.model.request.SignupRequest;
import com.yandiar.absensi.model.response.Response;
import com.yandiar.absensi.model.response.AuthResponse;
import com.yandiar.absensi.repository.UserRepository;
import com.yandiar.absensi.services.UserDetailsImpl;
import com.yandiar.absensi.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author YAR
 */

@CrossOrigin
@RestController
@RequestMapping("/auth")
@Api(value = "Auth API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Auth"})
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepo;

  @Autowired
  UserDetailsServiceImpl userService;

  @Autowired
  JwtUtils jwtUtils;

  @ApiOperation(value = "Sign In", response = AuthResponse.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 500, message = "Internal Server Error")
  })
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
        .ok(new AuthResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), jwt, roles));
  }

  @ApiOperation(value = "Sign Up", response = Response.class)
  @PostMapping("/signup")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
    if (userRepo.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST, "Username is already taken."));
    }

    if (userRepo.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST, "Email is already taken."));
    }

    Response res = userService.addUser(signupRequest);
    
    return ResponseEntity.ok(res);
  }
}
