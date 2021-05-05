package com.lawsystem.lawserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "can't prosecute on somethings that must be done if deadline in unmet")
public class DeadlineUnmetException extends Exception {
}
