package com.example.elastic.Controller;

import com.example.elastic.ElasticApi;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ElasticController {

    @Autowired
    ElasticApi elasticApi;

    @PostMapping("/insert")
    public String insert(){
        return "insert";
    }

    @GetMapping("/select")
    public Object select(){

        String url = "database/_search";
        Map<String, Object> result = elasticApi.callElasticApiAuth("GET", url, null, null);
        System.out.println(result.get("resultCode"));
        System.out.println(result.get("resultBody"));

        return result.get("resultBody");
    }

}
