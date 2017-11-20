package com.dmxiaoshen.dao;

import com.dmxiaoshen.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hzhsg on 2017/11/20.
 */
@Mapper
public interface UserDao {

    User selectByPrimaryKey(String id);
}
