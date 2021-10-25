package com.yandiar.absensi.repository;

import java.util.Optional;

import com.yandiar.absensi.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author YAR
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}
