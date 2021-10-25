package com.yandiar.absensi.model.request;

import java.io.Serializable;

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
public class LoginRequest implements Serializable {
  
  private String username;
  private String password;
}
