package org.example.machinglearn.Service.Impl;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.example.machinglearn.Service.ImageService;
import org.example.machinglearn.Util.HttpUtils;
import org.example.machinglearn.VO.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public Object getTongue(MultipartFile file) {
        String host = "https://ali-market-tongue-detect.macrocura.com";
        String path = "/diagnose/tongue/match/question/";
        String method = "POST";
        String appcode = "8a82e714ca0f4a989476d21725194f33";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("source_code", "0");

        String contentType = file.getContentType();
        byte[] fileBytes = null;
        try {
            fileBytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String base64 = Base64.getEncoder().encodeToString(fileBytes);
        bodys.put("image", base64);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                String jsonStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                System.out.println(jsonStr);
                //将jsonStr转换为Map
                Gson gson = new Gson();
                Map<String, Object> map = gson.fromJson(jsonStr, Map.class);
                if (map != null) {
                    Map<String, Object> data = (Map<String, Object>) map.get("data");
                    if (data != null) {
                        return ResultVO.success("获取成功", data);
                    }
                }
            } else {
                throw new RuntimeException("响应体为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object postTongue(MultipartFile file) {
	    String host = "https://ali-market-tongue-detect.macrocura.com";
	    String path = "/diagnose/tongue/inquiry/report/";
	    String method = "POST";
	    String appcode = "你自己的AppCode";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = "{\"session_id\":\"4a9f16e4-9a77-11ee-a379-00163e30cbb5\",\"basic_answers\":[{\"id\":5,\"answer\":\"2ca801d5-108e-4354-af86-622736ea157e\"},{\"id\":22,\"answer\":\"09167e22-be6d-4681-9afd-72cd6ebc7c9b\"}],\"addition_answers\":[{\"id\":1000000,\"answer\":\"1\"}]}";


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
            return response.getEntity();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

        return null;
    }
}
