package com.example.userservice.common.response;

import com.example.userservice.common.response.model.MetaCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Meta {

  private MetaCode type;
  private String code;
  private String message;

  public Meta(MetaCode metaCode) {
    this.type = metaCode;
    this.code = metaCode.code;
    this.message = null;
  }

  public Meta(MetaCode metaCode, String code, String message) {
    this.type = metaCode;
    this.code = code;
    this.message = message;
  }
}
