package ai.rezo.mshr.ManiHRBOOT.Utils;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHTTPMethods {
	private static final Logger logger =  LoggerFactory.getLogger(RestTemplateHTTPMethods.class);
	
	public static JSONObject PostMethod(String endpoint, HashMap<String, String> map) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<HashMap<String, String>> entity = new HttpEntity<HashMap<String, String>>(map ,headers);
		JSONObject jsonObject = null;

		try {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, entity,String.class);

		    if (responseEntity.getStatusCode() == HttpStatus.OK) {
		        try {
		            jsonObject = new JSONObject(responseEntity.getBody());
		        } catch (JSONException e) {
		            throw new RuntimeException("JSONException occurred while converting api response to jsonobject");
		        }
		    }
		  }
		catch(Exception e) {
			logger.error("Problem occured in RestAPI Request HTTPpost Method in resttemplate classs"+e.toString());
		}
		return jsonObject;
	}
}
