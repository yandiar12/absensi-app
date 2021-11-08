package com.yandiar.absensi.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pegawai", uniqueConstraints = { @UniqueConstraint(columnNames = "nik") })
public class Pegawai {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank
  @Size(max = 20)
  private String nik;

  @NotBlank
  @Size(max = 50)
  private String nama;

  @NotBlank
  @Size(max = 500)
  private String alamat;

  @NotBlank
  private Date tgl_lahir;

  @NotBlank
  @Size(max = 50)
  private String jabatan;

  @NotBlank
  @Size(max = 15)
  private String nohp;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @Size(max = 500)
  private String foto_profil;

  public Pegawai() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNik() {
    return nik;
  }

  public void setNik(String nik) {
    this.nik = nik;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public Date getTgl_lahir() {
    return tgl_lahir;
  }

  public void setTgl_lahir(Date tgl_lahir) {
    this.tgl_lahir = tgl_lahir;
  }

  public String getJabatan() {
    return jabatan;
  }

  public void setJabatan(String jabatan) {
    this.jabatan = jabatan;
  }

  public String getNohp() {
    return nohp;
  }

  public void setNohp(String nohp) {
    this.nohp = nohp;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFoto_profil() {
    return foto_profil;
  }

  public void setFoto_profil(String foto_profil) {
    this.foto_profil = foto_profil;
  }

  
}
