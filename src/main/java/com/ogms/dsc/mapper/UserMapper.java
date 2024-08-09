package com.ogms.dsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ogms.dsc.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @name: UserMapper
 * @description: TODO
 * @author: Lingkai Shi
 * @date: 4/10/2024 9:05 PM
 * @version: 1.0
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select user_id, user_name, email, password, join_time, last_login_time, status, use_space, total_space from user_info where user_id = #{userId}")
    User selectByUserId(String userId);

    IPage<User> selectPageByIds(Page<User> page, @Param("idList") List<Integer> idList);
}
