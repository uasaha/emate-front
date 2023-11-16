package me.emate.matefront.token.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthDto {

  private String memberNo;
  private String memberPwd;
  private List<String> authorities;
}