package com.lawsystem.lawserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "User is not registered")
public class UnregisteredMemberException extends Exception {

}
