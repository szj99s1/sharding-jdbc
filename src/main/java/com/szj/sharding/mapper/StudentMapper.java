package com.szj.sharding.mapper;
import com.szj.sharding.entity.Student;
import java.util.List;

public interface  StudentMapper {
    Integer insert(Student s);
    List<Student> findAll();
    List<Student> findByStudentIds(List<Integer> studentIds);
}
