package com.lawsystem.lawserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "invalid prosecution")
public class InvalidProsecution extends Exception{
}
