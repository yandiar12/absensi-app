package com.yandiar.absensi.model.response;

import java.io.Serializable;
import java.util.List;

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
public class AuthResponse implements Serializable {
  private String token;
  private Long id;
  private String username;
  private String email;
  private List<String> roles;
}
