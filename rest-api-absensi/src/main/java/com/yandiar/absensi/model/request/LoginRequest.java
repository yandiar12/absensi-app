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
public class LoginRequest implements Serializable {
  
  @ApiModelProperty(name = "username", example = "yandi", required = true, position = 0)
  private String username;

  @ApiModelProperty(name = "password", example = "yandi", required = true, position = 1)
  private String password;
}
