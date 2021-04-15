////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class SignInRequestDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
