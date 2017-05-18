package com.minorfish.iot.boss.tsdb.controller;

import com.minorfish.iot.boss.tsdb.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/mqtt", produces = "application/json;charset=utf-8;")
public class MqttController {


    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam("clientid") String clientid, @RequestParam("username") String username,
                       @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setStatus(200);
            return "";
        } catch (Exception e) {
            response.setStatus(400);
            return Result.error(e.getMessage()).toString();
        }
    }

    @RequestMapping(value = "/acl", method = RequestMethod.GET)
    @ResponseBody
    public String acl(@RequestParam("access") String access, @RequestParam("username") String username,
                      @RequestParam("clientid") String clientid,@RequestParam("ipaddr") String ipaddr,
                      @RequestParam("topic") String topic) {
        try {
            System.out.print("aaaaaaaa2");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage()).toString();
        }
    }


    @RequestMapping(value = "/superuser", method = RequestMethod.POST)
    @ResponseBody
    public String superuser(@RequestParam("clientid") String clientid, @RequestParam("username") String username
                            ,HttpServletResponse response) {
        try {
            System.out.print("aaaaaaaa3");
            response.setStatus(400);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
