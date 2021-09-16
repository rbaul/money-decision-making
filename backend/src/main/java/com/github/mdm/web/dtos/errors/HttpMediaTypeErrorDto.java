package com.github.mdm.web.dtos.errors;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;

import java.io.Serializable;

/**
 * @author Idan Rozenfeld
 */
@ApiModel("HttpMediaTypeError")
@Getter
@Setter
@Builder
public class HttpMediaTypeErrorDto implements Serializable {
    private static final long serialVersionUID = 7301072886218818L;

    private final MediaType mediaType;

}
