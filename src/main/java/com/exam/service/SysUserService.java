package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.SysUser;

/**
 * 系统用户 Service。
 *
 * @author exam
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据账号查询用户。
     *
     * @param username 账号
     * @return 用户，不存在返回 null
     */
    SysUser getByUsername(String username);
}
