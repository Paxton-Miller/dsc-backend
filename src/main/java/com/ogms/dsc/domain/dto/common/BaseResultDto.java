package com.ogms.dsc.domain.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @name: BaseResultDto
 * @description: 返回结果数据传输对象
 * @author: Lingkai Shi
 * @date: 4/19/2024 10:19 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回结果数据")
public class BaseResultDto<T> {

    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private Boolean status;

    @ApiModelProperty(value = "数据")
    private T result;

}
