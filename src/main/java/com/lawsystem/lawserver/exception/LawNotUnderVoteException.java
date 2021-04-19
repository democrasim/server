package com.lawsystem.lawserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Law is not accepting votes")
public class LawNotUnderVoteException extends Exception {
}
