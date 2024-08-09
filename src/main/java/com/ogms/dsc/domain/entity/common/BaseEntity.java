package com.ogms.dsc.domain.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @name: BaseEntity
 * @description: 实体类基类
 * @author: Lingkai Shi
 * @date: 4/18/2024 7:48 PM
 * @version: 1.0
 */
@Data
@MappedSuperclass
@ApiModel(description = "实体类基类")
public class BaseEntity implements Serializable {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private static final long serialVersionUID = 1L;

    @NotNull
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "数据库自增标识id字段", required = true)
    private Integer id;

    @Size(max = 255)
    private String description;

    @NotNull
    @ApiModelProperty(value = "创建人id", required = true)
    private Integer createUserId;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(value = "修改人id")
    private Integer modifyUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    @NotNull
    @ApiModelProperty(required = true)
    private Boolean isDeleted;
}