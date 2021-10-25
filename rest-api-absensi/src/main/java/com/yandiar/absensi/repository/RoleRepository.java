package com.yandiar.absensi.repository;

import java.util.Optional;

import com.yandiar.absensi.model.entity.ERole;
import com.yandiar.absensi.model.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
