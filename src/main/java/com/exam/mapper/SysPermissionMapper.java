package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限 Mapper。
 *
 * @author exam
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查询用户拥有的权限标识集合（通过用户-角色-权限三级关联）。
     *
     * @param userId 用户 id
     * @return 权限标识列表，如 ["question:list"]
     */
    @Select("SELECT DISTINCT p.perm_key FROM sys_permission p " +
            "JOIN sys_role_permission rp ON p.id = rp.permission_id " +
            "JOIN sys_user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<String> selectPermKeysByUserId(@Param("userId") Long userId);
}
