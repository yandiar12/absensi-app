package com.yandiar.absensi.services;

import com.yandiar.absensi.model.entity.User;
import com.yandiar.absensi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author YAR
 */

 @Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Username : "+username));

    return UserDetailsImpl.build(user);
  }
  
}
