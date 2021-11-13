package com.yandiar.absensi.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yandiar.absensi.model.entity.Pegawai;
import com.yandiar.absensi.model.request.AddPegawaiRequest;
import com.yandiar.absensi.model.request.UpdatePegawaiRequest;
import com.yandiar.absensi.model.response.Response;
import com.yandiar.absensi.services.PegawaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author YAR
 */

@RestController
@RequestMapping("/pegawai")
@Api(value = "Pegawai API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Pegawai"})
public class PegawaiController {
  
  @Autowired
  PegawaiService pgwService;

  @ApiOperation(value = "Find All", response = Pegawai.class)
  @GetMapping("/findall")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> findAll() {
    List<Pegawai> lstData = new ArrayList<>();
    lstData = pgwService.findAll();
    return ResponseEntity.ok(lstData);
  }

  @ApiOperation(value = "Find By Email", response = Pegawai.class)
  @GetMapping("/findbyemail")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> findByEmail(@RequestParam String email) {
    List<Pegawai> lstData = new ArrayList<>();
    lstData = pgwService.findByEmail(email);
    return ResponseEntity.ok(lstData);
  }

  @ApiOperation(value = "Add Pegawai", response = Response.class)
  @PostMapping("/add")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> add(@RequestBody AddPegawaiRequest data) {
    Response res = new Response();
    res = pgwService.addPegawai(data);
    
    if (res.getStatus() == HttpStatus.BAD_REQUEST) {
      return ResponseEntity.badRequest().body(res);  
    } else if (res.getStatus() == HttpStatus.UNPROCESSABLE_ENTITY) {
      return ResponseEntity.unprocessableEntity().body(res);
    }
    return ResponseEntity.ok(res);
  }

  @ApiOperation(value = "Update Pegawai", response = Response.class)
  @PostMapping("/update/{id}")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdatePegawaiRequest data) {
    Response res = new Response();
    res = pgwService.updatePegawai(id, data);
    
    if (res.getStatus() == HttpStatus.BAD_REQUEST) {
      return ResponseEntity.badRequest().body(res);  
    } else if (res.getStatus() == HttpStatus.UNPROCESSABLE_ENTITY) {
      return ResponseEntity.unprocessableEntity().body(res);
    }
    return ResponseEntity.ok(res);
  }

  @ApiOperation(value = "Delete Pegawai", response = Response.class)
  @DeleteMapping("/delete/{id}")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Response res = new Response();
    
    if (id == null) {
      return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST, "Id is required"));
    }
    
    Collection<?extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
    listAuthorities.addAll(authorities);

    GrantedAuthority roles = listAuthorities.stream()
    .filter(role -> "ROLE_ADMIN".equals(role.getAuthority()))
    .findAny()
    .orElse(null);

    if (roles == null) {
      res.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
      res.setMessage("User role does not meet requirement.");
      return ResponseEntity.unprocessableEntity().body(res);
    }  

    res = pgwService.deletePegawai(id);
    
    if (res.getStatus() == HttpStatus.BAD_REQUEST) {
      return ResponseEntity.badRequest().body(res);  
    } else if (res.getStatus() == HttpStatus.UNPROCESSABLE_ENTITY) {
      return ResponseEntity.unprocessableEntity().body(res);
    }
    return ResponseEntity.ok(res);
  }
}
