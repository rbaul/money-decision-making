package com.github.mdm.web.dtos;

import com.github.rbaul.spring.boot.activity_log.objects.ActivityLogStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ApiModel("Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActivityLogDto {

    private Long id;

    private String username;

    private String action;

    private Date time;

    private ActivityLogStatus status;
}
