package com.yandiar.absensi.model.request;

import java.io.Serializable;
import java.util.Set;

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
public class SignupRequest implements Serializable {

  private String username;
  private String email;
  private String password;
  private Set<String> role;
}
