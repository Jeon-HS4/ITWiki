package com.tukorea.admin.member.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Objects;

@Mapper
public interface LoginDao {
    // 로그인 회원 정보 조회
    public HashMap<String, Object> selectMemberForLogin(HashMap<String,Object> paramMap);

    // 최근 로그인 일시 갱신
    public int updataMemberRecentLoginDatetime(HashMap<String,Object> paramMap);


}
