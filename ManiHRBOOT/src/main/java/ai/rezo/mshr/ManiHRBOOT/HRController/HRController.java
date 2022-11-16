package ai.rezo.mshr.ManiHRBOOT.HRController;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.rezo.mshr.ManiHRBOOT.Service.MessageService;


@RestController
@RequestMapping("MarutiHR/Controller")
public class HRController {
	private static final Logger logger =  LoggerFactory.getLogger(HRController.class);
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/get")
	public ResponseEntity<String> get(){
		return new ResponseEntity<>("Hey unidentified creature in universe I am working fine" , HttpStatus.OK);
	}
	
	@PostMapping("/get_Messages")
	public ResponseEntity<String> get_welcome_Messages(@RequestBody String req){
		JSONObject response = new JSONObject();
		String call_triggered = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		response.put("call_start", call_triggered);
		try {
			JSONObject request = new JSONObject(req);
			if(!request.has("Mobile_Number")) {
				return new ResponseEntity<>("Mobile Number not found",HttpStatus.NO_CONTENT);
			}
			messageService.getMessages(response, request.getString("Mobile_Number"));
		}catch(Exception e) {
			logger.error("Exception occured in hrcontroller class in get welcome message" + e.toString());
			response.put("status", "Invalid");
			response.put("Reason", "Improper JSON format exception");
			return new ResponseEntity<>("Improper JSON format exception " , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response.toString() , HttpStatus.OK);
	}
	
	@PostMapping("/validate_number")
	public ResponseEntity<String> get_number(@RequestBody() String req) {
		JSONObject response = new JSONObject();
		try {
			JSONObject request = new JSONObject(req);
			logger.info("usermessage: "+req);
			if(!request.has("Message") || !request.getString("Message").contains(":")) {
				return new ResponseEntity<>("User_input not found",HttpStatus.NO_CONTENT);
			}
			String number = messageService.validate_Number(request.getString("Message"));
			if(Integer.parseInt(number) <= 0 || Integer.parseInt(number) > 2) {
				response.put("status", "Invalid");
				return new ResponseEntity<>(response.toString() , HttpStatus.OK);
			}else {
				response.put("found", number);
			}
			return new ResponseEntity<>(response.toString() , HttpStatus.OK);
		
		}catch(Exception e) {
			logger.error("Problem occured while validating user input one or two "+e.toString());
			response.put("status", "Invalid");
			response.put("Reason", "Improper JSON format exception");
			return new ResponseEntity<>("Improper JSON format exception ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/store_info")
	public ResponseEntity<String> store_info(@RequestBody() String req) {
		JSONObject response = new JSONObject();
		String call_end = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		try {
			JSONObject request = new JSONObject(req);
			request.put("call_end", call_end);
			if(!request.has("Mobile_Number")) {
				return new ResponseEntity<>("Mobile Number not found",HttpStatus.NO_CONTENT);
			}
			messageService.save_details(request);
			
		}catch(Exception e) {
			logger.error("Problem occured while validating user input one or two "+e.toString());
			response.put("status", "Invalid");
			response.put("Reason", "Improper JSON format exception");
			return new ResponseEntity<>("Improper JSON format exception ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Datas stored successfully" , HttpStatus.OK);
	}
	
}
