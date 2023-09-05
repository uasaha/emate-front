package me.emate.matefront.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenInfoDto {
    private String sub;
    private String memberUuid;
    private String roles;
    private String iat;
    private Long exp;
}
