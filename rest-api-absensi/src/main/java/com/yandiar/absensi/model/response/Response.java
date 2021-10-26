package com.yandiar.absensi.model.response;
import java.io.Serializable;

import org.springframework.http.HttpStatus;

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
public class Response implements Serializable {
  
  @ApiModelProperty(name = "Status", example = "OK", required = true, position = 0)
  private HttpStatus status;

  @ApiModelProperty(name = "Message", example = "Success", required = true, position = 1)
  private String message;
}
