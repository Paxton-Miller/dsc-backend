package com.ogms.dsc.domain.dto.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name: BaseQueryDto
 * @description: 查询数据传输对象基类
 * @author: Lingkai Shi
 * @date: 4/19/2024 5:00 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQueryDto {

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "每页请求数")
    private Integer pageSize = Integer.MAX_VALUE; // 不提供页码和页码数的情况下默认可以返回全部结果
}
