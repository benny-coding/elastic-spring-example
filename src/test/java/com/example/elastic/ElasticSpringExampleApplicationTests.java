package com.example.elastic;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSpringExampleApplicationTests {

	@Autowired
	ElasticApi elasticApi;

	private final String ELASTIC_INDEX = "rssdata";
	private final String ELASTIC_TYPE = "firstdata";

	@Test
	public void 엘라스틱서치_POST_전송() {
		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE;
		Weather weather = new Weather();
		weather.setCity("jeju");
		weather.setTemperature(40.2);
		weather.setSeason("Summer");

		Map<String, Object> result = elasticApi.callElasticApiAuth("POST", url, weather, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}


	@Test
	public void 엘라스틱서치_PUT_전송() {
		String id = "0000001";
		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		Weather weather = new Weather();
		weather.setCity("Tokyo");
		weather.setTemperature(12.3);
		weather.setSeason("Winter");

		Map<String, Object> result = elasticApi.callElasticApiAuth("PUT", url, weather, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}


	@Test
	public void 앨라스틱서치_GET_전송() {
		String id = "5mSg6m4Br6ylg6grz6Xh";
		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		Map<String, Object> result = elasticApi.callElasticApiAuth("GET", url, null, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}

	@Test
	public void 앨라스틱서치_모든_데이터_조회() {

		String url = "database/_search";
		Map<String, Object> result = elasticApi.callElasticApiAuth("GET", url, null, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}


	@Test
	public void 앨라스틱서치_DELETE_전송() {
		String id = "YGaO7m4Br6ylg6gr6dGc";
		String url = ELASTIC_INDEX + "/" + ELASTIC_TYPE+"/"+id;
		Map<String, Object> result = elasticApi.callElasticApiAuth("DELETE", url, null, null);
		System.out.println(result.get("resultCode"));
		System.out.println(result.get("resultBody"));
	}

}
