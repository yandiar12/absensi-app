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
public class UpdatePegawaiRequest implements Serializable {
  
  @ApiModelProperty(name = "nama", example = "USER", required = true, position = 1)
  private String nama;

  @ApiModelProperty(name = "alamat", example = "JL BANDA", required = true, position = 2)
  private String alamat;

  @ApiModelProperty(name = "tgl lahir", example = "01/01/2000", required = true, position = 3)
  private String tgl_lahir;

  @ApiModelProperty(name = "no hp", example = "08121231232122", required = true, position = 5)
  private String nohp;

  @ApiModelProperty(name = "jabatan", example = "PROGRAMMER", required = true, position = 6)
  private String jabatan;

  @ApiModelProperty(name = "foto profil", example = "user.png", required = false, position = 7)
  private String foto_profil;
}
