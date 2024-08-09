package com.ogms.dsc.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ogms.dsc.domain.dto.common.BaseQueryDto;
import com.ogms.dsc.domain.dto.common.BaseResultDto;
import com.ogms.dsc.domain.vo.PaginationResultVo;

/**
 * @name: IBaseService
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/5/2024 9:40 AM
 * @version: 1.0
 */
public interface IBaseService <T, AddDto, EditDto, QueryDto>{

    default BaseResultDto add(Integer createUserId, AddDto addDto) {
        throw new UnsupportedOperationException("Add operation is not supported.");
    }

    default BaseResultDto edit(Integer id, EditDto editDto) {
        throw new UnsupportedOperationException("Edit operation is not supported.");
    }

    default BaseResultDto delById(Integer id) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }

    default BaseResultDto queryById(Integer id) {
        throw new UnsupportedOperationException("Query operation is not supported.");
    }

    default PaginationResultVo paginate(QueryWrapper<T> queryWrapper, BaseQueryDto queryDto) {
        throw new UnsupportedOperationException("Page operation is not supported.");
    }
}
