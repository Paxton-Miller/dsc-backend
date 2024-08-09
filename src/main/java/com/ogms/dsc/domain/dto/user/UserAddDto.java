package com.ogms.dsc.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @name: UserAddDto
 * @description: 用户添加数据传输对象
 * @author: Lingkai Shi
 * @date: 5/7/2024 9:06 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "用户信息注册")
public class UserAddDto {
    @ApiModelProperty(required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(required = true)
    private String password;
}
