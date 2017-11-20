package com.dmxiaoshen.dao;

import com.dmxiaoshen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hzhsg on 2017/11/20.
 */
public interface UserRepository extends JpaRepository<User,String>{

    User findByName(String name);

    User findByNameAndAge(String name,Integer age);

    @Query("select u from User u where u.name=:name")
    User findUser(@Param("name")String name);
}
