package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.KnowledgePoint;
import com.exam.mapper.KnowledgePointMapper;
import com.exam.service.KnowledgePointService;
import org.springframework.stereotype.Service;

/**
 * 知识点 Service 实现。
 *
 * @author exam
 */
@Service
public class KnowledgePointServiceImpl extends ServiceImpl<KnowledgePointMapper, KnowledgePoint> implements KnowledgePointService {
}
