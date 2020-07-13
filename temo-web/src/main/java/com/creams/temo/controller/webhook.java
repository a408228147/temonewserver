package com.creams.temo.controller;


import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.tools.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class webhook {
    private static Logger logger = LoggerFactory.getLogger(webhook.class);
    @PostMapping("/gitlab")
    public JsonResult gitlab(@RequestBody String content){
        //提交测试

        logger.info(content);
        return new JsonResult("success",200,content,true);
    }

}
