package com.example.userservice.common.response;

import com.example.userservice.common.response.model.MetaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO<T> {

  private final T data;
  private final Meta meta;

  public ResponseDTO(Meta meta, T data) {
    this.meta = meta;
    this.data = data;
  }

  public static <T> ResponseDTO<T> success(Meta meta, T data) {
    return new ResponseDTO<>(meta, data);
  }

  public static <T> ResponseDTO<T> success(MetaCode metaCode, T data) {
    return success(new Meta(metaCode), data);
  }

  public static <T> ResponseDTO<T> success(T data) {
    return success(MetaCode.SUCCESS, data);
  }
}
