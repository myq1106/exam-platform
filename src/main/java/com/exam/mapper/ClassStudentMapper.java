package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.ClassStudent;
import com.exam.vo.ClassStudentVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 班级-学生关联 Mapper。
 *
 * @author exam
 */
public interface ClassStudentMapper extends BaseMapper<ClassStudent> {

    /**
     * 查询班级下的学生列表，JOIN sys_user 获取学生信息。
     *
     * @param classId 班级 id
     * @return 班级学生展示对象集合
     */
    @Select("SELECT cs.id, cs.class_id AS classId, cs.student_id AS studentId, " +
            "u.username, u.real_name AS realName, u.phone " +
            "FROM class_student cs " +
            "LEFT JOIN sys_user u ON cs.student_id = u.id " +
            "WHERE cs.class_id = #{classId}")
    List<ClassStudentVO> selectStudentsByClassId(@Param("classId") Long classId);
}
