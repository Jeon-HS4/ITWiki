package com.tukorea.itwiki.user.controller;

import com.tukorea.itwiki.user.dto.LoginForm;
import com.tukorea.itwiki.user.dto.SignupForm;
import com.tukorea.itwiki.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {

    private LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }


    @GetMapping("/login")
    public String login() {
        return "login/login";
    }


    @ResponseBody
    @PostMapping("/login")
    public HashMap<String, Object> loginAjax(LoginForm loginForm, HttpServletRequest request) {
        // login Service 메서드 호출
        HashMap<String, Object> resultMap = service.login(loginForm);
        // 성공 시 session에 회원 정보 저장
        String result_cd = resultMap.get("result_cd").toString();
        if ("00".equals(result_cd)) {
            HttpSession session = request.getSession();
            HashMap<String, Object> memberMap = (HashMap<String, Object>) resultMap.get("member");
            session.setAttribute("sUserId", memberMap.get("userId"));
            session.setAttribute("sUsername", memberMap.get("username"));
            session.setAttribute("sRole", memberMap.get("role"));
            ((HttpSession) session).setAttribute("sEmail", memberMap.get("email"));
        }
        return resultMap;
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
    @GetMapping("/signUp")
    public String signUp(){return "login/signUp";}

    @ResponseBody
    @PostMapping("/signup")
    public HashMap<String, Object> signupAjax(SignupForm signupForm, HttpServletRequest request) {
        // login Service 메서드 호출
        HashMap<String, Object> resultMap = service.signup(signupForm);
        // 성공 시 session에 회원 정보 저장
        String result_cd = resultMap.get("result_cd").toString();
        if ("00".equals(result_cd)) {
            HttpSession session = request.getSession();
            HashMap<String, Object> memberMap = (HashMap<String, Object>) resultMap.get("member");
            session.setAttribute("sUserId", memberMap.get("userId"));
            session.setAttribute("sUsername", memberMap.get("username"));
            ((HttpSession) session).setAttribute("sEmail", memberMap.get("email"));
        }
        return resultMap;
    }

}
