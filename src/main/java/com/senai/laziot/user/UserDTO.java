package com.senai.laziot.user;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter  @Setter  @ToString  @AllArgsConstructor  @NoArgsConstructor
public class UserDTO {

    private String nome;
    private String sobrenome;
    private String senha;
}
