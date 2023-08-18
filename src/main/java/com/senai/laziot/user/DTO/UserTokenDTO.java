package com.senai.laziot.user.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDTO {

    private String userEmail;
    private String userUniqueHashCode;
}
