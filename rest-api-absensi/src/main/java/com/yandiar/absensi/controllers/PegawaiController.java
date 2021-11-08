package com.yandiar.absensi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.yandiar.absensi.model.entity.Pegawai;
import com.yandiar.absensi.model.request.AddPegawaiRequest;
import com.yandiar.absensi.model.response.Response;
import com.yandiar.absensi.services.PegawaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @ApiOperation(value = "Pegawai", response = Pegawai.class)
  @GetMapping("/findall")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataType = "string")
  })
  public ResponseEntity<?> findAll() {
    List<Pegawai> lstData = new ArrayList<>();
    lstData = pgwService.findAll();
    return ResponseEntity.ok(lstData);
  }

  @ApiOperation(value = "Pegawai", response = Response.class)
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
}
