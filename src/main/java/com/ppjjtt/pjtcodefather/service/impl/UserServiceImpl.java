package com.ppjjtt.pjtcodefather.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ppjjtt.pjtcodefather.model.entity.User;
import com.ppjjtt.pjtcodefather.mapper.UserMapper;
import com.ppjjtt.pjtcodefather.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author ppjjtt
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{

}
