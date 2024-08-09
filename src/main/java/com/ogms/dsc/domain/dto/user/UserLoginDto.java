package com.ogms.dsc.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @name: UserLoginDto
 * @description: 用户登录请求参数数据传输对象
 * @author: Lingkai Shi
 * @date: 5/6/2024 10:55 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "用户登录请求参数")
public class UserLoginDto {
    @NotBlank
    @ApiModelProperty(required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(required = true)
    private String password;
}
