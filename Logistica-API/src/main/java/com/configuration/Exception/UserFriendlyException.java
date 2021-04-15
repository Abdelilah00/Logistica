////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UserFriendlyException extends Exception {
    private static final long serialVersionUID = 1L;

    @NotNull
    private final String message;
    private final HttpStatus httpStatus = null;
}
