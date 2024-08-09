package com.ogms.dsc.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ogms.dsc.domain.entity.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @name: User
 * @description: 用户实体类
 * @author: Lingkai Shi
 * @date: 4/10/2024 8:16 PM
 * @version: 1.0
 */
@Data
@TableName("users")
@ApiModel(description = "用户实体类")
public class User extends BaseEntity { // 序列化的主要目的是将对象持久化，使得对象可以在不同的程序、不同的计算机之间进行传输和共享，或者保存到磁盘上以便后续读取和使用

    // @TableField("name") // 使用自动映射
    @Size(max = 255)
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(required = true)
    private String email;

    @Size(max = 255)
    private String nickname;

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(required = true)
    private String password;

    @NotBlank
    private String avatar;

    @NotNull
    @ApiModelProperty(example = "true", notes = "1表示普通用户", required = true)
    private Boolean type;

    @NotNull
    @ApiModelProperty(required = true)
    private Boolean isEnable;
}
