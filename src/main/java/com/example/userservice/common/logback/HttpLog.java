package com.example.userservice.common.logback;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpLog {

  public static final String REQUEST_ID = "request-id";
  public static final String REMOTE_SERVICE = "remote-service";
  public static final String METHOD = "method";
  public static final String URL = "url";
  public static final String HEADERS = "headers";
  public static final String PARAMETERS = "parameters";
  public static final String CONTENT_TYPE = "content-type";
  public static final String REQUEST = "request";
  public static final String RESPONSE = "response";
  public static final String STATUS = "status";
  public static final String X_REQUEST_ID = "x-request-id";

}
