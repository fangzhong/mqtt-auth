package com.minorfish.iot.boss.tsdb.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Result {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private STATUS status;
    private int code;
    private String message;
    private Object result;

    public Result() {
        super();
    }

    public Result(STATUS status, Integer code, String message, Object result) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public STATUS getStatus() {
        return status;
    }
    public void setStatus(STATUS status) {
        this.status = status;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }

    public static Result error(int code, String message, Object result){
        return new Result(STATUS.ERROR, code, message, result);
    }

    public static Result error(int code, String message){
        return error(code,message,null);
    }

    public static Result error(String message){
        return error(Result.STATUS.ERROR.getValue(),message,null);
    }

    public static Result ok(int code, String message, Object result){
        return new Result(STATUS.OK, code, message, result);
    }

    public static Result ok(int code,String message){
        return ok(code,message,null);
    }

    public static Result ok(String message){
        return ok(Result.STATUS.OK.getValue(),message);
    }

    public static Result ok(String message, Object result) {
        return ok(Result.STATUS.OK.getValue(), message, result);
    }


    public enum STATUS{
        OK(200),  		//OK
        ERROR(400); 	//ERROR

        private int value;

        STATUS(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    private static SerializeConfig config = new SerializeConfig();

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, config, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
    }

    public String toGson(ExclusionStrategy strategy) {
        Gson gson = new GsonBuilder().setExclusionStrategies(strategy).create();
        return gson.toJson(this);
    }

//    public String toGsonString() {
//        return WxGsonBuilder.create().toJson(this);
//    }

}
