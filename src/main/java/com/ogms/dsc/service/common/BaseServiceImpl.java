package com.ogms.dsc.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.dsc.domain.dto.common.BaseQueryDto;
import com.ogms.dsc.domain.dto.common.BaseResultDto;
import com.ogms.dsc.domain.vo.PaginationResultVo;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @name: BaseServiceImpl
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 8/5/2024 9:56 AM
 * @version: 1.0
 */
public abstract class BaseServiceImpl<T, AddDto, EditDto, QueryDto> extends ServiceImpl<BaseMapper<T>, T> implements IBaseService<T, AddDto, EditDto, QueryDto> {

    protected abstract BaseMapper<T> getMapper();

    protected abstract T dto2Po(AddDto addDto);

    protected abstract T getById(Integer id);

    @Override
    public BaseResultDto add(Integer createUserId, AddDto addDto) {
        try {
            T entity = dto2Po(addDto);
            // Set createUserId if necessary
            Method setCreateUserId = entity.getClass().getMethod("setCreateUserId", Integer.class);
            setCreateUserId.invoke(entity, createUserId);
            getMapper().insert(entity);
            // Get Id
            Method getId = entity.getClass().getMethod("getId");
            entity = getMapper().selectById((Serializable) getId.invoke(entity));
            return new BaseResultDto<>(true, entity);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto<T> edit(Integer id, EditDto editDto) {
        try {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public BaseResultDto delById(Integer id) {
        try {
            T entity = this.getById(id);
            // Suppose every entity has a setIsDeleted method
            Method setIsDeletedMethod = entity.getClass().getMethod("setIsDeleted", Boolean.class);
            setIsDeletedMethod.invoke(entity, true);
            getMapper().updateById(entity);
            return new BaseResultDto<>(true, true);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto<T> queryById(Integer id) {
        try {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public PaginationResultVo paginate(QueryWrapper<T> queryWrapper, BaseQueryDto queryDto) {
        Page<T> page = new Page<>(queryDto.getPage(), queryDto.getPageSize());
        getMapper().selectPage(page, queryWrapper);

        return new PaginationResultVo(page.getTotal(),
                queryDto.getPageSize(),
                queryDto.getPage(),
                page.getPages(),
                page.getRecords());
    }

//    protected abstract Serializable getEntityId(T entity);
}
