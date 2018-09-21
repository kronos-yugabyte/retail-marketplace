package io.pivotal.cronos.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
public class DashboardRestConsumer {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${cronos.yugabyte.api:http://localhost:8080/api/v1}")
	String restUrlBase;

	public String getHomePageProducts(int limit) {

		String restURL = restUrlBase + "/productmetadata/search/products?limit=" + limit;
		ResponseEntity<String> rateResponse =
		        restTemplate.exchange(restURL,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		            });
		String productListJsonResponse = rateResponse.getBody();

		JsonObject jsonObject = new Gson().fromJson(productListJsonResponse, JsonObject.class);
		JsonObject productListjsonObject = jsonObject.get("_embedded").getAsJsonObject();
		JsonElement productListJsonArray = productListjsonObject.get("productMetadatas").deepCopy();
		return productListJsonArray.toString();
	}

	public String getProductDetails(String asin) {

		String restURL = restUrlBase + "/productmetadata/" + asin;
		ResponseEntity<String> rateResponse =
		        restTemplate.exchange(restURL,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		            });
		String productDetailsJsonResponse = rateResponse.getBody();
		return productDetailsJsonResponse;
	}	
}