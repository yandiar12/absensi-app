package com.yandiar.absensi.repository;

import java.util.List;

import com.yandiar.absensi.model.entity.Pegawai;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author YAR
 */
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {
  Boolean existsByEmail(String email);

  List<Pegawai> findByEmail(String email);
}
