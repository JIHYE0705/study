package com.fastcampus.study.controller;


import com.fastcampus.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api

public class GetController {

    @RequestMapping(method = RequestMethod.GET,path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")    // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbbb";
        System.out.println("id : "+id);
        System.out.println("pwd : "+pwd);

        return id+pwd;
    }

    // localhost:8080/api/multiParameter?accout=abcd&email=study@gmail.com&page=10

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParmeter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" : "", "page" : 0}
        return searchParam;
    }



}
