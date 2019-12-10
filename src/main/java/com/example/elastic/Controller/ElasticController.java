package com.example.elastic.Controller;

import com.example.elastic.ElasticApi;
import com.example.elastic.RssData;
import com.example.elastic.Weather;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class ElasticController {

    @Autowired
    ElasticApi elasticApi;

    SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");


    @PostMapping("/insert")
    public Object insert(@RequestParam Map<String, String> param){

        String index = param.get("index");
        String type = param.get("type");
        String title = param.get("title");
        String contents = param.get("contents");
        String writer = param.get("writer");

        String url = index + "/" + type;
        RssData rssData = new RssData();
        rssData.setTitle(title);
        rssData.setContents(contents);
        rssData.setWriter(writer);
        rssData.setCreateDateTime(format1.format(new Date()));

        Map<String, Object> result = elasticApi.callElasticApiAuth("POST", url, rssData, null);

        return result.get("resultBody");
    }

    @GetMapping("/search")
    public Object search(@RequestParam Map<String, String> param){

        String index = param.get("index");
        String query = param.get("query");
        try {
            query = URLDecoder.decode(query,"EUC-KR");
        } catch (Exception e){

        }

        String url = index + "/_search" + query;
        System.out.println(url);
        Map<String, Object> result = elasticApi.callElasticApiAuth("GET", url, null, null);

        return result.get("resultBody");
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestParam Map<String, String> param){

        String index = param.get("index");
        String type = param.get("type");
        String id = param.get("id");

        System.out.println(index);
        System.out.println(type);
        System.out.println(id);

        String url = index + "/" + type+ "/" + id;

        Map<String, Object> result = elasticApi.callElasticApiAuth("DELETE", url, null, null);

        return result.get("resultBody");

    }

}
