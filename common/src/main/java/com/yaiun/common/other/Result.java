package com.yaiun.common.other;

public class Result {
    private int code;
    private String msg;
    private int count;//总数
    private Object data;//数据

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result build(int code, int count, Object data, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setCount(count);
        result.setData(data);
        return result;
    }

    public static Result ok(int count, Object data) {
        return build(0, count, data, "操作成功");
    }

    public static Result ok() {
        return build(0, 0, null, "操作成功");
    }

    public static Result fail(String msg) {
        return build(0, 0, null, msg);
    }

    public static Result fail() {
        return fail("操作失败");
    }

}
