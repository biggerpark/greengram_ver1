package com.green.greengramver1.user;

import com.green.greengramver1.user.model.UserSignInReq;
import com.green.greengramver1.user.model.UserSignUpReq;
import com.green.greengramver1.user.model.UserSingInRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpReq p);
    UserSingInRes selUserForSignIn(UserSignInReq p);


}
