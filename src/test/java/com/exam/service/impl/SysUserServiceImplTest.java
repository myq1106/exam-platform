package com.exam.service.impl;

import com.exam.entity.SysUser;
import com.exam.mapper.SysUserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link SysUserServiceImpl} 单元测试（Mockito）。
 *
 * @author exam
 */
@ExtendWith(MockitoExtension.class)
class SysUserServiceImplTest {

    @Mock
    private SysUserMapper sysUserMapper;

    private SysUserServiceImpl sysUserService;

    @BeforeEach
    void setUp() {
        sysUserService = new SysUserServiceImpl();
        // ServiceImpl 的 baseMapper 字段位于父类，手动注入 mock
        ReflectionTestUtils.setField(sysUserService, "baseMapper", sysUserMapper);
    }

    @Test
    void getByUsername_shouldReturnUser_whenExists() {
        SysUser user = new SysUser();
        user.setId(1L);
        user.setUsername("teacher1");

        when(sysUserMapper.selectOne(any())).thenReturn(user);

        SysUser result = sysUserService.getByUsername("teacher1");

        assertNotNull(result);
        assertEquals("teacher1", result.getUsername());
        verify(sysUserMapper).selectOne(any());
    }

    @Test
    void getByUsername_shouldReturnNull_whenNotFound() {
        when(sysUserMapper.selectOne(any())).thenReturn(null);

        SysUser result = sysUserService.getByUsername("nobody");

        assertNull(result);
    }
}
