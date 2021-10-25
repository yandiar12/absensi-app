package com.yandiar.absensi.model.response;
import java.io.Serializable;

import org.springframework.http.HttpStatus;

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
public class ApiResponse implements Serializable {
  private HttpStatus status;
  private String message;
}
