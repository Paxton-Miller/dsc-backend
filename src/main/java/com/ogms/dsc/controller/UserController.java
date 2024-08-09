package com.ogms.dsc.controller;

import com.ogms.dsc.common.R;
import com.ogms.dsc.domain.constants.Constants;
import com.ogms.dsc.domain.dto.common.BaseQueryDto;
import com.ogms.dsc.domain.dto.common.BaseResultDto;
import com.ogms.dsc.domain.dto.user.UserAddDto;
import com.ogms.dsc.domain.dto.user.UserLoginDto;
import com.ogms.dsc.domain.dto.user.UserQueryDto;
import com.ogms.dsc.domain.entity.User;
import com.ogms.dsc.domain.vo.PaginationResultVo;
import com.ogms.dsc.service.IUserService;
import com.ogms.dsc.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: UserController
 * @description: 用户控制器
 * @author: Lingkai Shi
 * @date: 4/10/2024 7:33 PM
 * @version: 1.0
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(value = "User API", tags = {"User"})
public class UserController {
    @Autowired
    private IUserService userService; // 自动将实现了 IUserService 接口的 Bean 对象注入到 userService 字段中

    @ApiOperation("获取所有用户数据")
    @GetMapping("/getAll")
    public R<PaginationResultVo> getAllUser(@ModelAttribute BaseQueryDto query) {
        return R.success(userService.getAllUser(query), "获取成功");
    }

    @ApiOperation("通过id获取用户数据")
    @GetMapping("/getById")
    public R<User> getUserById(Integer id) {
        return R.success(userService.getById(id), "获取成功");
    }

    @ApiOperation("通过idList获取用户数据")
    @GetMapping("/getByIds")
    public R<PaginationResultVo> getUserByIds(@ModelAttribute UserQueryDto query) {
        return R.success(userService.getUserByIds(query), "获取成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody UserLoginDto userLoginDto) {
        try {
            BaseResultDto loginResultDto = userService.login(userLoginDto);
            if (loginResultDto.getStatus()) {
                request.getSession().setAttribute(Constants.SESSION_KEY, loginResultDto.getResult());
                return R.success(loginResultDto.getResult());
            } else {
                request.getSession().removeAttribute(Constants.CHECK_CODE_KEY);
                return R.fail(loginResultDto.getResult().toString().trim());
            }
        } finally {
            request.getSession().removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @ApiOperation("用户登出")
    @GetMapping("/logout")
    public R logout() {
        return R.handleService(() -> userService.logout());
    }

    @ApiOperation("用户注册")
    @PostMapping("/add")
    public R addUser(HttpServletRequest request, @RequestBody UserAddDto userAddDto) {
        Integer createUserId = null;
        try {
            createUserId = (Integer) JwtUtils.decodeToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization"))).get("id");
        } finally {
            if (createUserId == null)
                return R.handleService(() -> userService.addUser(userAddDto));
            else {
                Integer finalCreateUserId = createUserId;
                return R.handleService(() -> userService.addUser(finalCreateUserId, userAddDto));
            }
        }
    }
}
