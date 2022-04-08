package com.lyq.eca.controller;

import com.lyq.eca.pojo.Result;
import com.lyq.eca.pojo.Test;
import com.lyq.eca.pojo.User;
import com.lyq.eca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *　　┏┓　　　┏┓+ +
 *　┏┛┻━━━┛┻┓ + +
 *　┃　　　　　　　┃ 　
 *　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 *　┃　　　　　　　┃ +
 *　┃　　　┻　　　┃
 *　┃　　　　　　　┃ + +
 *　┗━┓　　　┏━┛
 *　　　┃　　　┃　　　　　　　　　　　
 *　　　┃　　　┃ + + + +
 *　　　┃　　　┃
 *　　　┃　　　┃ +  神兽保佑
 *　　　┃　　　┃    代码无bug　　
 *　　　┃　　　┃　　+　　　　　　　　　
 *　　　┃　 　　┗━━━┓ + +
 *　　　┃ 　　　　　　　┣┓
 *　　　┃ 　　　　　　　┏┛
 *　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　┃┫┫　┃┫┫
 *　　　　┗┻┛　┗┻┛+ + + +
 */

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        //对html标签进行转义，防止xss攻击
        String username=requestUser.getUsername();
        username= HtmlUtils.htmlEscape(username);
        User user=userService.get(username,requestUser.getPassword());
        if(null==user){
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
    @CrossOrigin
    @PostMapping(value = "api/getusr")
    @ResponseBody
    public User getusr(@RequestBody User requestUser){
        User user=userService.findByUsername(requestUser.getUsername());
        return user;
    }

    @CrossOrigin
    @PostMapping(value="api/updatephoto")
    @ResponseBody
    public Result updatePhoto(@RequestBody User requestUser){
        if(userService.updatephotoByUsername(requestUser.getUsername(),requestUser.getPhoto())){
            return new Result(200);
        }
        else return new Result(400);
    }

    @CrossOrigin
    @GetMapping(value = "api/test")
    @ResponseBody
    public List<Test> getchartdata(){
        List<Test> t=new ArrayList<>();
        Test t1=new Test("9998","长安区");
        Test t2=new Test("10220","碑林区");
        t.add(t1);
        t.add(t2);
        return t;
    }

}
