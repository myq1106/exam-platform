package com.exam.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link JwtUtil} 单元测试。
 *
 * @author exam
 */
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        // secret 长度必须 >= 32 字节
        jwtUtil = new JwtUtil("unit-test-jwt-secret-key-0123456789abcdefghij", 3600);
    }

    @Test
    void generateAndParseToken() {
        String token = jwtUtil.generateToken("teacher1", 1L,
                List.of("TEACHER"), List.of("question:list"));

        assertNotNull(token);
        assertEquals("teacher1", jwtUtil.getUsername(token));
        assertEquals(1L, jwtUtil.getUserId(token));
    }
}
