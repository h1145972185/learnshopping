package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import com.neuedu.utils.MD5Utils;
import com.neuedu.utils.TokenCache;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/*
* service层调用dao层
* */

//将UserServiceImpl类交给容器管理
//标识为业务逻辑层的一个类
@Service
public class UserServiceImpl  implements IUserService {
    //自动封装
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 注册
     */
    @Override
    public ServerResponse register(UserInfo userInfo) {


        //step1  非空校验

        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(), ResponseCode.PARAM_EMPTY.getMsg());
        }
        //step2  检查用户名是否存在
        String username = userInfo.getUsername();
        /*
        int  result=  userInfoMapper.checkUsername(username);
      if (result>0){//用户名存在
          return  ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(),ResponseCode.EXISTS_USERNAME.getMsg());
      }*/

        ServerResponse serverResponse = check_valid(username, Const.USERNAME);
        if (!serverResponse.isSuccess()) {
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(), ResponseCode.EXISTS_USERNAME.getMsg());
        }
        //step3  检查邮箱是否存在

        ServerResponse email_serverResponse = check_valid(userInfo.getEmail(), Const.EMAIL);
        if (!email_serverResponse.isSuccess()) {
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_EMAIL.getStatus(), ResponseCode.EXISTS_EMAIL.getMsg());
        }

        //step4  进行注册
        userInfo.setRole(Const.USER_ROLE_CUSTOMER);
        //使用MD5工具类进行密码加密
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        int insert_result = userInfoMapper.insert(userInfo);
        //step5  返回结果
        if (insert_result > 0) {
            return ServerResponse.createServerResponseBySucess("注册成功");
        }
        return ServerResponse.createServerResponseBySucess("注册失败");
    }

    /**
     * 登录
     */
    @Override
    public ServerResponse login(String username, String password) {

        //step1:参数非空校验
//StringUtils.isEmpty()与StringUtils.isBlank()的区别在于 如果字符串" "中有空格isBlank也认为他是空的，而isEmpty认为是非空的
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ServerResponse.createServerResponseByError("密码不能为空");
        }
        //step2:检查username是否存在
        ServerResponse serverResponse = check_valid(username, Const.USERNAME);
        if (serverResponse.isSuccess()) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOT_EXISTS_USERNAME.getStatus(), ResponseCode.NOT_EXISTS_USERNAME.getMsg());
        }
        //step3：根据用户名和密码查询            将密码密文化  与数据库中的密文密码相匹配
        UserInfo userInfo = userInfoMapper.selectUserByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
        if (userInfo == null) {//密码错误
            return ServerResponse.createServerResponseByError("密码错误");
        }
        //step4：处理结果并返回
        userInfo.setPassword("");
        return ServerResponse.createServerResponseBySucess(null, userInfo);
    }


    /**
     * 检验用户名是否有效
     * */
    @Override
    public ServerResponse check_valid(String str, String type) {
        //step1 参数的非空校验
        if (StringUtils.isBlank(str) || StringUtils.isBlank(type)) {
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2 判断用户名或者邮箱是否存在
        if (type.equals(Const.USERNAME)) {
            int username_result = userInfoMapper.checkUsername(str);
            if (username_result > 0) {
                return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_USERNAME.getStatus(), ResponseCode.EXISTS_USERNAME.getMsg());
            }
            return ServerResponse.createServerResponseBySucess("成功");
        } else if (type.equals(Const.EMAIL)) {
            int email_result = userInfoMapper.checkEmail(str);
            if (email_result > 0) {
                return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_EMAIL.getStatus(), ResponseCode.EXISTS_EMAIL.getMsg());
            }
            return ServerResponse.createServerResponseBySucess("成功");
        }

        //step3 返回结果
        return ServerResponse.createServerResponseByError("type参数传递有误");
    }


    /**
     * 根据用户名查找密保问题
     */

    @Override
    public ServerResponse forget_get_question(String username) {

        //step1 非空校验
        if (username == null || username.equals("")) {
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }

        //step2  校验username

        int result = userInfoMapper.checkUsername(username);
        if (result == 0) {
            //用户名不存在
            return ServerResponse.createServerResponseByError("用户不存在，请重新输入");
        }
        //step3  查找密保问题

        String question = userInfoMapper.selectQuestionByUsername(username);

        if (question == null || question.equals("")) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOQUESTION.getStatus(), ResponseCode.NOQUESTION.getMsg());
        }
        return ServerResponse.createServerResponseBySucess(question);
    }

    /**
     * 提交问题答案
     */


    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        //1、校验参数
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(), ResponseCode.PARAM_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(question)) {
            return ServerResponse.createServerResponseByError(ResponseCode.NOQUESTION.getStatus(), ResponseCode.NOQUESTION.getMsg());
        }
        if (StringUtils.isBlank(answer)) {
            return ServerResponse.createServerResponseByError(ResponseCode.ANSWER_NULL.getStatus(), ResponseCode.ANSWER_NULL.getMsg());
        }
        //2、根据username,question,answer查询
        int resule = userInfoMapper.selectByUsernameAndQuestionAndAnswer(username, question, answer);
        if (resule <= 0) {
            return ServerResponse.createServerResponseByError(ResponseCode.ANSWER_ERROR.getStatus(), ResponseCode.ANSWER_ERROR.getMsg());
        }

        //UUID随机生成一个唯一的字符串
        //3、服务端生成token保存并将token返回给客户端
        String forgettoken = UUID.randomUUID().toString();
        //guava cache
        TokenCache.set(username, forgettoken);
        return ServerResponse.createServerResponseBySucess(null, forgettoken);
    }

    @Override
    public ServerResponse forget_reset_password(String username, String passwordNew, String forgetToken) {

        //step1  参数校验

        if (StringUtils.isBlank(username) || StringUtils.isBlank(passwordNew) || StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createServerResponseByError(ResponseCode.PARAM_EMPTY.getStatus(), ResponseCode.PARAM_EMPTY.getMsg());
        }
        // step2  校验token是否一致
        String token = TokenCache.get(username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createServerResponseByError(ResponseCode.EXISTS_TOKEN.getStatus(), ResponseCode.EXISTS_TOKEN.getMsg());
        }
        if (!forgetToken.equals(token)) {
            return ServerResponse.createServerResponseByError(ResponseCode.TOKEN_ERROR.getStatus(), ResponseCode.TOKEN_ERROR.getMsg());
        }

        //step3  修改密码
        int result = userInfoMapper.updatePasswordByUsername(username, MD5Utils.getMD5Code(passwordNew));
        if (result <= 0) {
            return ServerResponse.createServerResponseByError(ResponseCode.PASSWORD_ERROR.getStatus(), ResponseCode.PASSWORD_ERROR.getMsg());
        }
        //step4  返回结果
        return ServerResponse.createServerResponseBySucess();
    }

    @Override
    public ServerResponse reset_password(UserInfo userInfo, String passwordOld, String passwordNew) {
        //1、参数校验
        if (StringUtils.isBlank(passwordOld) || StringUtils.isBlank(passwordNew)) {
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //2、校验旧密码
        UserInfo userInfo1 = userInfoMapper.selectUserByUsernameAndPassword(userInfo.getUsername(), MD5Utils.getMD5Code(passwordOld));
        if (userInfo == null) {
            return ServerResponse.createServerResponseByError(ResponseCode.ANSWER_ERROR.getStatus(), ResponseCode.ANSWER_ERROR.getMsg());
        }
        //3、修改密码
        int resule_update = userInfoMapper.updatePasswordByUsername(userInfo.getUsername(), MD5Utils.getMD5Code(passwordNew));

        //4、处理结果并返回
        if (resule_update > 0) {
            return ServerResponse.createServerResponseBySucess("修改成功");
        }
        return ServerResponse.createServerResponseByError("修改失败");
    }

    @Override
    public ServerResponse Adminlogin(String username, String password){
            //step1:参数非空校验
            if (StringUtils.isBlank(username)) {
                return ServerResponse.createServerResponseByError("用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                return ServerResponse.createServerResponseByError("密码不能为空");
            }
            //step2:检查username是否存在
            ServerResponse serverResponse = check_valid(username, Const.USERNAME);
            if (serverResponse.isSuccess()) {
                return ServerResponse.createServerResponseByError(ResponseCode.NOT_EXISTS_USERNAME.getStatus(), ResponseCode.NOT_EXISTS_USERNAME.getMsg());
            }
            //step3：根据用户名和密码查询            将密码密文化  与数据库中的密文密码相匹配
            UserInfo userInfo = userInfoMapper.selectUserByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
            if (userInfo == null) {//密码错误
                return ServerResponse.createServerResponseByError("密码错误");
            }
            //step4：处理结果并返回
            userInfo.setPassword("");
            return ServerResponse.createServerResponseBySucess(null, userInfo);
        }

    }




