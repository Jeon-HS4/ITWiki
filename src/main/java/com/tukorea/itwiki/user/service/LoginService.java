package com.tukorea.itwiki.user.service;

import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.user.dao.LoginDao;
import com.tukorea.itwiki.user.dto.LoginForm;
import com.tukorea.itwiki.user.dto.Signup;
import com.tukorea.itwiki.user.dto.SignupForm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginService {

    private LoginDao dao;

    @Autowired
    public LoginService(LoginDao dao){
        this.dao = dao;
    }

    public HashMap<String, Object> login(LoginForm loginForm){
        HashMap<String, Object> resultMap = new HashMap<>();

        String result_cd = "00";
        String result_msg = "정상";

        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", loginForm.getId());
            paramMap.put("password", loginForm.getPassword());
            // 로그인 정보와 일치하는 회원 확인
            HashMap<String, Object> memberMap = dao.selectMemberForLogin(paramMap);
            if (memberMap == null) { throw new Exception("로그인 정보가 일치하지 않습니다."); }

            resultMap.put("member",memberMap);

        }catch (Exception ex){
            result_cd = "99";
            result_msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            resultMap.put("result_cd", result_cd);
            resultMap.put("result_msg", result_msg);
        }
        return resultMap;
    }


    public HashMap<String, Object> signup(SignupForm signupForm){
        HashMap<String, Object> resultMap = new HashMap<>();

        String result_cd = "00";
        String result_msg = "정상";

        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", signupForm.getUserId());
            String pwd1 = signupForm.getPassword();
            String pwd2 = signupForm.getPasswordCheck();

            // 로그인 정보와 일치하는 회원 확인
            HashMap<String, Object> memberID = dao.checkDuplicateMember(paramMap);

            if (memberID != null) { throw new Exception("이미 사용중인 아이디입니다."); }

            if (!pwd1.equals(pwd2)){ throw new Exception("비밀번호가 일치하지 않습니다."); }

            HashMap<String, Object> memberMap = new HashMap<>();
            memberMap.put("userId",signupForm.getUserId());
            memberMap.put("password",signupForm.getPassword());
            memberMap.put("username",signupForm.getUsername());
            memberMap.put("email",signupForm.getEmail());

            resultMap.put("member",memberMap);

            Signup user = new Signup();
            user.setUserId(signupForm.getUserId());
            user.setPassword(signupForm.getPassword());
            user.setUsername(signupForm.getUsername());
            user.setEmail(signupForm.getEmail());

            // 게시판 등록
            int resultCount = dao.insertNewUser(user);

        }catch (Exception ex){
            result_cd = "99";
            result_msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            resultMap.put("result_cd", result_cd);
            resultMap.put("result_msg", result_msg);
        }
        return resultMap;
    }


}
