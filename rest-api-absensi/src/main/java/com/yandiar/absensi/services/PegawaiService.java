package com.yandiar.absensi.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.yandiar.absensi.model.entity.Pegawai;
import com.yandiar.absensi.model.entity.User;
import com.yandiar.absensi.model.request.AddPegawaiRequest;
import com.yandiar.absensi.model.request.SignupRequest;
import com.yandiar.absensi.model.request.UpdatePegawaiRequest;
import com.yandiar.absensi.model.response.Response;
import com.yandiar.absensi.repository.PegawaiRepository;
import com.yandiar.absensi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author YAR
 */
@Service
@Slf4j
public class PegawaiService {
  
  @Autowired
  PegawaiRepository pgwRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  UserDetailsServiceImpl userService;

  private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  public List<Pegawai> findAll() {
    List<Pegawai> lstData = pgwRepo.findAll();
    log.info("Pegawai find all : {}", lstData.size());
    return lstData;
  }

  public List<Pegawai> findByEmail(String email) {
    List<Pegawai> lstData = pgwRepo.findByEmail(email);
    log.info("Pegawai find by email");
    return lstData;
  }

  public Response addPegawai(AddPegawaiRequest data) {
    Response res = new Response();
    Pegawai pgw = new Pegawai();

    Boolean pgwExists = pgwRepo.existsByEmail(data.getEmail());

    if (pgwExists) {
      res.setStatus(HttpStatus.BAD_REQUEST);
      res.setMessage("Email is already exists");
      return res;
    }

    try {
      Date dateTglLahir = formatter.parse(data.getTgl_lahir());

      pgw.setNik(data.getNik());
      pgw.setNama(data.getNama().toUpperCase());
      pgw.setAlamat(data.getAlamat().toUpperCase());
      pgw.setEmail(data.getEmail());
      pgw.setTgl_lahir(new java.sql.Date(dateTglLahir.getTime()));
      pgw.setJabatan(data.getJabatan().toUpperCase());
      pgw.setNohp(data.getNohp());
      pgw.setFoto_profil(data.getFoto_profil());
      pgwRepo.save(pgw);

      SignupRequest signupRequest = new SignupRequest();
      signupRequest.setEmail(data.getEmail());
      signupRequest.setUsername(data.getUsername());
      signupRequest.setRole(null);
      signupRequest.setPassword(data.getPassword());

      Response resUser = userService.addUser(signupRequest);
      
      if (resUser.getStatus() == HttpStatus.OK) {
        res.setStatus(HttpStatus.OK);
        res.setMessage("Pegawai successfully added");
      } else {
        res.setStatus(resUser.getStatus());
        res.setMessage(resUser.getMessage());
      }

    } catch (Exception ex) {
      ex.printStackTrace();
      res.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
      res.setMessage(ex.getLocalizedMessage());
    }
    return res;
  }

  public Response updatePegawai(Long id, UpdatePegawaiRequest data) {
    Response res = new Response();
    Pegawai pgw = new Pegawai();

    Boolean pgwExists = pgwRepo.existsById(id);

    if (!pgwExists) {
      res.setStatus(HttpStatus.BAD_REQUEST);
      res.setMessage("Id Pegawai does not exists");
      return res;
    }

    try {
      Date dateTglLahir = formatter.parse(data.getTgl_lahir());

      pgw.setId(id);
      pgw.setNama(data.getNama().toUpperCase());
      pgw.setAlamat(data.getAlamat().toUpperCase());
      pgw.setTgl_lahir(new java.sql.Date(dateTglLahir.getTime()));
      pgw.setJabatan(data.getJabatan().toUpperCase());
      pgw.setNohp(data.getNohp());
      pgw.setFoto_profil(data.getFoto_profil());
      pgwRepo.save(pgw);

      res.setStatus(HttpStatus.OK);
      res.setMessage("Pegawai successfully updated.");

    } catch (Exception ex) {
      ex.printStackTrace();
      res.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
      res.setMessage(ex.getLocalizedMessage());
    }
    return res;
  }

  public Response deletePegawai(Long id) {
    Response res = new Response();

    Optional<Pegawai> pegawai = pgwRepo.findById(id);

    if (!pegawai.isPresent()) {
      res.setStatus(HttpStatus.BAD_REQUEST);
      res.setMessage("Id Pegawai does not exists");
      return res;
    }

    Optional<User> user = userRepo.findByEmail(pegawai.get().getEmail());
    if (!user.isPresent()) {
      res.setStatus(HttpStatus.BAD_REQUEST);
      res.setMessage("User does not exists");
      return res;
    }

    try {

      userRepo.delete(user.get());
      pgwRepo.deleteById(id);

      res.setStatus(HttpStatus.OK);
      res.setMessage("Pegawai successfully updated.");

    } catch (Exception ex) {
      ex.printStackTrace();
      res.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
      res.setMessage(ex.getLocalizedMessage());
    }
    return res;
  }
}
