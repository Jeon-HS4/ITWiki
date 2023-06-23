package com.tukorea.itwiki.admin.dao;

import com.tukorea.itwiki.admin.domain.Revision;
import com.tukorea.itwiki.user.dto.Signup;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {
    // 로그인 회원 정보 조회
    public HashMap<String, Object> selectMemberForLogin(HashMap<String,Object> paramMap);
    public HashMap<String, Object> checkDuplicateMember(HashMap<String,Object> paramMap);
    public int insertNewUser(Signup user);

}
