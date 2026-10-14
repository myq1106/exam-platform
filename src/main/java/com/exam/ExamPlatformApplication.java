package com.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在线考试平台启动类。
 *
 * @author exam
 */
@SpringBootApplication
@MapperScan("com.exam.mapper")
public class ExamPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamPlatformApplication.class, args);
    }

}
