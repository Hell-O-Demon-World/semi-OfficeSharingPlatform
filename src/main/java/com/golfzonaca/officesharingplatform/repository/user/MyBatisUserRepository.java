package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.mybatis.dto.UserInfoDto;
import com.golfzonaca.officesharingplatform.repository.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository {

    private UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.save(user);
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public int countContainByEmail(String email) {
        return userMapper.countContainByEmail(email);
    }

    @Override
    public User update(long id, UserInfoDto updateParam) {
        return userMapper.update(id, updateParam);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
