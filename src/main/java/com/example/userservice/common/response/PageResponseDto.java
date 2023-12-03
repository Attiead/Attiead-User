package com.example.userservice.common.response;

import com.example.userservice.common.response.model.MetaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PageResponseDto<T> {

  private final Page<T> data;
  private final Meta meta;

  public PageResponseDto(Meta meta, Page<T> data) {
    this.meta = meta;
    this.data = data;
  }

  public static <T> PageResponseDto<T> success(Meta meta, Page<T> data) {
    return new PageResponseDto<>(meta, data);
  }

  public static <T> PageResponseDto<T> success(MetaCode metaCode, Page<T> data) {
    return success(new Meta(metaCode), data);
  }

  public static <T> PageResponseDto<T> success(Page<T> data) {
    return success(MetaCode.SUCCESS, data);
  }
}
