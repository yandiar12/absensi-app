package com.yandiar.absensi.services;

import java.util.HashSet;
import java.util.Set;

import com.yandiar.absensi.model.entity.ERole;
import com.yandiar.absensi.model.entity.Role;
import com.yandiar.absensi.model.entity.User;
import com.yandiar.absensi.model.request.SignupRequest;
import com.yandiar.absensi.model.response.Response;
import com.yandiar.absensi.repository.UserRepository;
import com.yandiar.absensi.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author YAR
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepo;

  @Autowired
  RoleRepository roleRepo;

  @Autowired
  PasswordEncoder encoder;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Username : "+username));

    return UserDetailsImpl.build(user);
  }
  
  public Response addUser(SignupRequest signupRequest) {
    Response res = new Response();

    try {
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
    } catch (Exception ex) {
      ex.printStackTrace();
      res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      res.setMessage("Error add user : "+ex.getLocalizedMessage());
    }

    res.setStatus(HttpStatus.OK);
    res.setMessage("User successfully registered");

    return res;
  }

}
