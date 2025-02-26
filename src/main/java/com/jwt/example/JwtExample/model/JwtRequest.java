package com.jwt.example.JwtExample.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtRequest {

    private String email;
    private String password;
}
