package com.yth.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    //点击左侧的文章的时候，跳转到一个页面
    @RequestMapping("/blog-post")
    public String blog_post() {
        return "/articles/blog-post";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index-2")
    public String index_2() {
        return "/index-2";
    }

    @RequestMapping("/index-3")
    public String index_3() {
        return "/index-3";
    }

    //通过点击文章题目或者了解更多时，跳转到对应页面
    @RequestMapping("/Spring")
    public String Spring() {
        return "/articles/Spring";
    }

    @RequestMapping("/SpringMVC")
    public String SpringMVC() {
        return "/articles/SpringMVC";
    }

    @RequestMapping("/Vue")
    public String Vue() {
        return "/articles/Vue";
    }

    @RequestMapping("/SpringBoot1")
    public String SpringBoot1() {
        return "/articles/SpringBoot-1";
    }

    @RequestMapping("/SpringBoot2")
    public String SpringBoot2() {
        return "/articles/SpringBoot-2";
    }

    @RequestMapping("/JDBC")
    public String JDBC_learn() {
        return "/articles/JDBC";
    }

    @RequestMapping("/GOF23--cjx")
    public String GOF23_cjx() {
        return "/articles/GOF23--cjx";
    }

    @RequestMapping("/GOF23--jgx")
    public String GOF23_jgx() {
        return "/articles/GOF23--jgx";
    }

    @RequestMapping("/GOF23--xwx")
    public String GOF23_xwx() {
        return "/articles/GOF23--xwx";
    }

    @RequestMapping("/JavaWeb")
    public String JavaWeb() {
        return "/articles/JavaWeb";
    }

    @RequestMapping("/HTML")
    public String HTML() {
        return "/articles/HTML";
    }

    @RequestMapping("/CSS-1")
    public String CSS_1() {
        return "/articles/CSS-1";
    }

    @RequestMapping("/CSS-2")
    public String CSS_2() {
        return "/articles/CSS-2";
    }

    @RequestMapping("/CSS-3")
    public String CSS_3() {
        return "/articles/CSS-3";
    }

    @RequestMapping("/CSS-4")
    public String CSS_4() {
        return "/articles/CSS-4";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    //用于测试
    @RequestMapping("/test")
    public String test() {
        return "test";
    }


}