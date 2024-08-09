package com.ogms.dsc.domain.dto.user;

import com.ogms.dsc.domain.dto.common.BaseQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @name: UserQueryDto
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 5/26/2024 4:36 PM
 * @version: 1.0
 */
@Data
@ApiModel(description = "请求参数")
@NoArgsConstructor
public class UserQueryDto extends BaseQueryDto {

    @NotNull
    @ApiModelProperty(value = "用户idList", required = true)
    private List<Integer> idList;

    public UserQueryDto(Integer page, Integer pageSize, List<Integer> idList) {
        super(page, pageSize);
        this.idList = idList;
    }
}
