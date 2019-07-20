package com.szj.sharding.service.impl;

import com.szj.sharding.entity.Student;
import com.szj.sharding.mapper.StudentMapper;
import com.szj.sharding.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    public StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;
    }
}
