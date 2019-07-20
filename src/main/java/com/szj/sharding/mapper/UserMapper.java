package com.szj.sharding.mapper;

import com.szj.sharding.entity.User;

import java.util.List;

public interface UserMapper {
    Integer insert(User u);
    List<User> findAll();
    List<User> findByUserIds(List<Integer> userIds);
}
