package com.ogms.dsc.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @name: PaginationResultVo
 * @description: 分页返回数据前端响应对象
 * @author: Lingkai Shi
 * @date: 4/19/2024 7:08 PM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@ApiModel(description = "分页返回数据")
public class PaginationResultVo<T> {
    @ApiModelProperty("总数")
    private Long totalCount;

    @ApiModelProperty("每页请求数")
    private Integer pageSize;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("总页数")
    private Long pageTotal;

    @ApiModelProperty("当页数据")
    private List<T> list;
}