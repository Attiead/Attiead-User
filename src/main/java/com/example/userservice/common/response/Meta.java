package com.example.userservice.common.response;

import com.example.userservice.common.response.model.MetaCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Meta {
    private MetaCode metaCode;
    private String type;
    private String message;

    public Meta(MetaCode metaCode) {
        this.metaCode = metaCode;
        this.type = metaCode.name();
        this.message = null;
    }
}
