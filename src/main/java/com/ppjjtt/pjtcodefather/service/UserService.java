package com.ppjjtt.pjtcodefather.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.ppjjtt.pjtcodefather.model.dto.user.UserQueryRequest;
import com.ppjjtt.pjtcodefather.model.entity.User;
import com.ppjjtt.pjtcodefather.vo.LoginUserVO;
import com.ppjjtt.pjtcodefather.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * 用户 服务层。
 *
 * @author ppjjtt
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id！
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    //专门用来把User转换为脱敏后的LoginUserVO
    LoginUserVO getLoginUserVO(User user);

    //同样是脱敏，不过相比登录，这里返回更少的信息，相比登录来说
    UserVO getUserVO(User user);

    //查询到的多个信息
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 根据查询参数获取查询条件包装器
     * @param userQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);


    String getEncryptPassword(String userPassword);
}
