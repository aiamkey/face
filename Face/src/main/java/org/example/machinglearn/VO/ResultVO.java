package org.example.machinglearn.VO;

import lombok.Data;

/**
 * @author 张宇
 * @version 1.0
 * @date 2025/4/29 22:20
 */

@Data
public class ResultVO {
    //返回数据的格式
    private int code;
    private String msg;
    private Object data;

    public static ResultVO success(String msg, Object data){
        ResultVO vo = new ResultVO();
        vo.setCode(200);
        vo.setMsg(msg);
        vo.setData(data);
        return vo;
    }

    public static ResultVO error(String msg){
        ResultVO vo = new ResultVO();
        vo.setCode(500);
        vo.setMsg(msg);
        vo.setData(null);
        return vo;
    }

    public static ResultVO data(int code, String msg, Object data){
        ResultVO vo = new ResultVO();
        vo.setCode(code);
        vo.setMsg(msg);
        vo.setData(data);
        return vo;
    }

}
