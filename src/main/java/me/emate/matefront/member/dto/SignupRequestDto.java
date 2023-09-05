package me.emate.matefront.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class SignupRequestDto {
    @NotBlank
    private String memberId;
    @NotBlank
    @Setter
    private String pwd;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String intro;
}
