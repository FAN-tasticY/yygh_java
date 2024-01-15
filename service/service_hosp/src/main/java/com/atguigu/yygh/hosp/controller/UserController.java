package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.R;
import com.atguigu.yygh.model.acl.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@CrossOrigin
public class UserController {

    @PostMapping("/login")
    public R login(@RequestBody User user){
        return R.ok().data(user.getUsername(),"admin-token");
    }

    @GetMapping("/info")
    // 如果这里的参数和前端传递过来的参数名称一致的话，那么就可以省略@requestParam这个注解的，如果不一致就需要加上这个注解
    public R info(String token){
        if (!"".equals(token)){
            return R.ok().data("roles","[admin]").data("introduction","I am a super administrator")
                    .data("avatar","https://5b0988e595225.cdn.sohucs.com/images/20191018/5fc2807259cc4e89a3455f302e4a5a3e.gif")
                    .data("name","系统管理员");
        }else {
            return R.error().data("error","没有token");
        }
    }
}
