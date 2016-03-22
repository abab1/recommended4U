package com.recommendations.dao.impl;

import org.springframework.stereotype.Repository;

import com.recommendations.dao.UserDao;
import com.recommendations.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

}
