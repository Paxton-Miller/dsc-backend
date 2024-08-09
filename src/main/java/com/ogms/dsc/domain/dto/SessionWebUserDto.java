package com.ogms.dsc.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @name: SessionWebUserDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 4/18/2024 4:38 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "用户缓存")
public class SessionWebUserDto {

    private int id;

    private String email;

    private Boolean type;

    private String token;
}
