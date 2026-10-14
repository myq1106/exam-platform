package com.exam.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.dto.req.UserSaveReq;
import com.exam.entity.SysUser;
import com.exam.entity.SysUserRole;
import com.exam.exception.BusinessException;
import com.exam.service.SysUserRoleService;
import com.exam.service.SysUserService;
import com.exam.util.Result;
import com.exam.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理接口（演示 RBAC 权限控制：仅 ADMIN 可增删改）。
 *
 * @author exam
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysUserRoleService sysUserRoleService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 分页查询用户。
     */
    @Operation(summary = "分页查询用户")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/page")
    public Result<Page<UserVO>> page(@RequestParam(defaultValue = "1") long pageNum,
                                     @RequestParam(defaultValue = "10") long pageSize,
                                     @RequestParam(required = false) String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(username), SysUser::getUsername, username)
                .orderByDesc(SysUser::getCreateTime);
        Page<SysUser> page = sysUserService.page(new Page<>(pageNum, pageSize), wrapper);

        Page<UserVO> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        voPage.setRecords(page.getRecords().stream().map(this::toVO).toList());
        return Result.success(voPage);
    }

    /**
     * 根据 id 查询用户。
     */
    @Operation(summary = "查询用户详情")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return Result.success(toVO(user));
    }

    /**
     * 新增用户。
     */
    @Operation(summary = "新增用户")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Result<Void> create(@Validated @RequestBody UserSaveReq req) {
        if (StrUtil.isBlank(req.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        if (sysUserService.getByUsername(req.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }
        SysUser user = new SysUser();
        BeanUtil.copyProperties(req, user);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setStatus(req.getStatus() == null ? 1 : req.getStatus());
        sysUserService.save(user);

        // 分配角色
        if (CollUtil.isNotEmpty(req.getRoleIds())) {
            List<SysUserRole> userRoles = req.getRoleIds().stream().map(roleId -> {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                return ur;
            }).toList();
            sysUserRoleService.saveBatch(userRoles);
        }
        return Result.success();
    }

    /**
     * 删除用户。
     */
    @Operation(summary = "删除用户")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.success();
    }

    /**
     * entity -> vo，避免直接返回 entity。
     */
    private UserVO toVO(SysUser user) {
        UserVO vo = new UserVO();
        BeanUtil.copyProperties(user, vo);
        return vo;
    }
}
