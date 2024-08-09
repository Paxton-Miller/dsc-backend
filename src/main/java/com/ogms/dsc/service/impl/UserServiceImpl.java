package com.ogms.dsc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ogms.dsc.domain.converter.UserConverter;
import com.ogms.dsc.domain.dto.SessionWebUserDto;
import com.ogms.dsc.domain.dto.common.BaseQueryDto;
import com.ogms.dsc.domain.dto.common.BaseResultDto;
import com.ogms.dsc.domain.dto.user.UserAddDto;
import com.ogms.dsc.domain.dto.user.UserLoginDto;
import com.ogms.dsc.domain.dto.user.UserQueryDto;
import com.ogms.dsc.domain.entity.User;
import com.ogms.dsc.domain.vo.PaginationResultVo;
import com.ogms.dsc.mapper.UserMapper;
import com.ogms.dsc.service.IUserService;
import com.ogms.dsc.utils.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @name: UserServiceImpl
 * @description: 用户服务层
 * @author: Lingkai Shi
 * @date: 4/10/2024 8:57 PM
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    // UserMapper负责对数据库进行操作，ServiceImpl对应IService，MyBatis之一，负责ORM
    // 潜在有list等方法，供Controller调用，不必在此重写

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;

    @Override
    public PaginationResultVo getAllUser(BaseQueryDto query) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", false);
        Page<User> page = new Page<>(query.getPage(), query.getPageSize());
        userMapper.selectPage(page, queryWrapper);

        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    @Override
    public PaginationResultVo getUserByIds(UserQueryDto query) {
        Page<User> page = new Page<>(query.getPage(), query.getPageSize());
        userMapper.selectPageByIds(page, query.getIdList());
        PaginationResultVo paginationResultVo = new PaginationResultVo(page.getTotal(),
                query.getPageSize(), query.getPage(), page.getPages(), page.getRecords());
        return paginationResultVo;
    }

    /*@Override
    public BaseResultDto login(UserLoginDto userLoginDto) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUserDetailsDto loginUserDetailsDto = (LoginUserDetailsDto) authenticate.getPrincipal();
        String jwt = JwtUtils.createToken(loginUserDetailsDto.getUser());
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 把完整用户信息存入redis，id为key
        redisCache.setCacheObject("login:" + loginUserDetailsDto.getUser().getId(), loginUserDetailsDto); ////
        return new BaseResultDto<>(true, map);

    }
*/
    // 老方法，不用SpringSecurity
    @Override
    public BaseResultDto login(UserLoginDto userLoginDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userLoginDto.getEmail());
        List<User> userList = this.list(queryWrapper);
        if (userList.isEmpty()) {
            // 如果没有此账户
            return new BaseResultDto<>(false, "账户不存在");
        }
        User user = userList.get(0);
        if (!BCrypt.checkpw(userLoginDto.getPassword(), user.getPassword())) {
            // 如果密码错误
            return new BaseResultDto<>(false, "密码错误");
        }
        SessionWebUserDto sessionWebUserDto = userConverter.po2Dto(user);
        sessionWebUserDto.setToken(JwtUtils.createToken(user));
        return new BaseResultDto<>(true, sessionWebUserDto);
    }

    @Override
    public BaseResultDto logout() {
        try {
            /*// 获取SecurityHolder中的用户id
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            LoginUserDetailsDto loginUserDetailsDto = (LoginUserDetailsDto) authentication.getPrincipal();
            Integer id = loginUserDetailsDto.getUser().getId();
            // 删除Redis中的值
            redisCache.deleteObject("login:" + id);*/
        } catch (Exception e) {
            return new BaseResultDto<>(false, e.getMessage());
        }
        return new BaseResultDto<>(true, true);
    }

    @Override
    public BaseResultDto addUser(UserAddDto userAddDto) {
        // 查询是否是已有帐户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userAddDto.getEmail());
        if (!this.list(queryWrapper).isEmpty()) {
            return new BaseResultDto<>(false, "账户已存在");
        }
        // 加密
        userAddDto.setPassword(BCrypt.hashpw(userAddDto.getPassword(), BCrypt.gensalt()));
        try {
            User user = userConverter.dto2Po(userAddDto);
            userMapper.insert(user);
            user = userMapper.selectById(user.getId());
            // 更新create_user_id默认为id本人
            user.setCreateUserId(user.getId());
            this.updateById(user);

            return new BaseResultDto<>(true, user);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }

    @Override
    public BaseResultDto addUser(Integer createUserId, UserAddDto userAddDto) {
        // 查询是否是已有帐户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userAddDto.getEmail());
        if (!this.list(queryWrapper).isEmpty()) {
            return new BaseResultDto<>(false, "账户已存在");
        }
        // 加密
        userAddDto.setPassword(BCrypt.hashpw(userAddDto.getPassword(), BCrypt.gensalt()));
        try {
            User user = userConverter.dto2Po(userAddDto);
            user.setCreateUserId(createUserId);
            userMapper.insert(user);
            user = userMapper.selectById(user.getId());
            return new BaseResultDto<>(true, user);
        } catch (Exception ex) {
            return new BaseResultDto<>(false, ex.getMessage());
        }
    }
}
