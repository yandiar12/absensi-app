package com.yandiar.absensi.model.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 
 * @author YAR
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@ApiModel
public class AuthResponse implements Serializable {
  @ApiModelProperty(name = "ID", example = "1", required = true, position = 0)
  private Long id;

  @ApiModelProperty(name = "Username", example = "yandi", required = true, position = 1)
  private String username;

  @ApiModelProperty(name = "Email", example = "yandi@gmail.com", required = true, position = 2)
  private String email;

  @ApiModelProperty(name = "Token", example = "generated token", required = true, position = 3)
  private String token;
  
  @ApiModelProperty(name = "Roles", example = "['ADMIN']", required = true, position = 4)
  private List<String> roles;
}
