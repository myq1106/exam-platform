package com.exam.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 学生加入班级入参。
 *
 * @author exam
 */
@Data
public class ClassJoinReq {

    /** 邀请码 */
    @NotBlank(message = "邀请码不能为空")
    private String inviteCode;
}
