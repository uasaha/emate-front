package me.emate.matefront.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthDto {
    private String memberNo;
    private String memberPwd;
    private List<String> authorities;
}