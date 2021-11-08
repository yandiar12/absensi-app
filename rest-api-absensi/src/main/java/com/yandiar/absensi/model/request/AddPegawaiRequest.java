package com.yandiar.absensi.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 
 * @author YAR
 */

@ApiModel 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddPegawaiRequest implements Serializable {
  
  @ApiModelProperty(name = "nik", example = "0192029302919202", required = true, position = 0)
  private String nik;

  @ApiModelProperty(name = "nama", example = "USER", required = true, position = 1)
  private String nama;

  @ApiModelProperty(name = "alamat", example = "JL BANDA", required = true, position = 2)
  private String alamat;

  @ApiModelProperty(name = "tgl lahir", example = "01/01/2000", required = true, position = 3)
  private String tgl_lahir;

  @ApiModelProperty(name = "email", example = "user@user.com", required = true, position = 4)
  private String email;

  @ApiModelProperty(name = "no hp", example = "08121231232122", required = true, position = 5)
  private String nohp;

  @ApiModelProperty(name = "jabatan", example = "PROGRAMMER", required = true, position = 6)
  private String jabatan;

  @ApiModelProperty(name = "foto profil", example = "user.png", required = false, position = 7)
  private String foto_profil;

  @ApiModelProperty(name = "username", example = "user", required = true, position = 8)
  private String username;

  @ApiModelProperty(name = "password", example = "123456", required = true, position = 9)
  private String password;
}
