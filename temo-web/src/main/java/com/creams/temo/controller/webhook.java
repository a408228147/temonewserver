package com.creams.temo.controller;


import com.creams.temo.entity.result.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class webhook {

    @PostMapping("/gitlab")
    public JsonResult gitlab(@RequestBody String content){
        return new JsonResult("success",200,content,true);
    }

}
