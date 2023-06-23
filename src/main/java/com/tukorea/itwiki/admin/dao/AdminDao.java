package com.tukorea.itwiki.user.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import com.tukorea.itwiki.user.dto.Signup;

@Mapper
public interface LoginDao {
    // 로그인 회원 정보 조회
    public HashMap<String, Object> selectMemberForLogin(HashMap<String,Object> paramMap);
    public HashMap<String, Object> checkDuplicateMember(HashMap<String,Object> paramMap);
    public int insertNewUser(Signup user);

}
