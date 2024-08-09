package com.ogms.dsc.domain.converter;

import com.ogms.dsc.domain.dto.SessionWebUserDto;
import com.ogms.dsc.domain.dto.user.UserAddDto;
import com.ogms.dsc.domain.entity.User;
import org.mapstruct.Mapper;

/**
 * @name: UserConverter
 * @description: 数据对象转换器
 * @author: Lingkai Shi
 * @date: 5/6/2024 10:10 PM
 * @version: 1.0
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    SessionWebUserDto po2Dto(User user);

    User dto2Po(UserAddDto userAddDto);
}
