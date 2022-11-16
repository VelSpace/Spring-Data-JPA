package ai.rezo.mshr.ManiHRBOOT.Service;
import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.rezo.mshr.ManiHRBOOT.Models.CallLogs;
import ai.rezo.mshr.ManiHRBOOT.Models.MasterData;
import ai.rezo.mshr.ManiHRBOOT.Repository.CallLogsRepository;
import ai.rezo.mshr.ManiHRBOOT.Repository.MasterRepository;
import ai.rezo.mshr.ManiHRBOOT.Utils.MSHRFilePaths;
import ai.rezo.mshr.ManiHRBOOT.Utils.RestTemplateHTTPMethods;
import ai.rezo.mshr.ManiHRBOOT.Utils.SwaggerProperty;
import ai.rezo.mshr.ManiHRBOOT.Utils.utils;

@Service
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	static Properties prop;
	static {
		prop = utils.loadPropertyValues(MSHRFilePaths.PROPERTY_FILE_PACKAGE);
	}
	
	@Autowired
	private MasterRepository masterrepo;
	
	@Autowired
	private CallLogsRepository callLogsRepository;
	

	public JSONObject getMessages(JSONObject response, String Mobile_Number) {
		MasterData curr_Object = masterrepo.findBymobilephone(Mobile_Number);
		if(curr_Object.equals(null)) {
			response.put("first_message", "");
			return response.put("message1", prop.getProperty("incoming_hangup"));
		}
		try {
			String call_type = curr_Object.getCall_type();
			String test_name = curr_Object.getCategory();
			String[] date_and_time = getDateAndTime(curr_Object);
			String date = date_and_time[0];
			String time = date_and_time[1];
			String place = curr_Object.getAddress();
			logger.info(curr_Object.getCall_type());
			String msg1 = "";
			String msg2 = "";
			response.put("master_id", curr_Object.getId());
			if (curr_Object.getIs_interested() == null && call_type.equalsIgnoreCase("rt pcr")) {
				msg1 = prop.getProperty("rtpcr_resp");
				msg2 = prop.getProperty("repeat_rtpcr");
				response.put("first_message", "");
				response.put("feedback", prop.getProperty("confirm_resp"));
			} else if (curr_Object.getIs_interested() == null && call_type.equalsIgnoreCase("assessment")) {
				msg1 = prop.getProperty("Assessment_resp");
				msg2 = prop.getProperty("repeat_assessment");
				response.put("first_message", "");
				response.put("feedback", prop.getProperty("confirm_resp"));
			} else if (curr_Object.getIs_interested() == null && call_type.equalsIgnoreCase("interview")) {
				msg1 = prop.getProperty("interview_resp");
				msg2 = prop.getProperty("repeat_interview");
				response.put("first_message", "");
				response.put("feedback", prop.getProperty("confirm_resp"));
			} else if (curr_Object.getIs_interested() == null && call_type.equalsIgnoreCase("joining")) {
				msg1 = prop.getProperty("joining_resp");
				response.put("first_message", "");
			} else if (curr_Object.getIs_interested() == null && !call_type.equalsIgnoreCase("joining")) {
				response.put("feedback", prop.getProperty("feedback_received_hindi"));
				response.put("first_message", curr_Object.getIs_interested());
			} else {
				response.put("feedback", prop.getProperty("feedback_received_hindi"));
				response.put("first_message", curr_Object.getIs_interested());
			}
			msg1 = msg1.replace("{test}", test_name);
			msg1 = msg1.replace("{date}", date);
			msg1 = msg1.replace("{time}", time);
			msg1 = msg1.replace("{place}", place);
			msg2 = msg2.replace("{test}", test_name);
			msg2 = msg2.replace("{date}", date);
			msg2 = msg2.replace("{time}", time);
			msg2 = msg2.replace("{place}", place);
			response.put("message1", msg1);
			response.put("againMessage1", msg2);
			response.put("call_type", call_type);
			response.put("interested_master",
					curr_Object.getIs_interested() == null ? "" : curr_Object.getIs_interested());
			return response;
		} catch (Exception e) {
			logger.error("error occured while connecting DB in message property class" + e.toString());
			return response;
		}
	}

	private static String[] getDateAndTime(MasterData curr_Object) throws SQLException {
		String date = "";
		if (curr_Object.getDate_appointment() != null) {
			Date date_obj = curr_Object.getDate_appointment();

			Calendar cal = Calendar.getInstance();
			cal.setTime(date_obj);
			String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			date = cal.get(Calendar.DAY_OF_MONTH) + " " + month;
		}
		String time = "";
		if (curr_Object.getTime_appointment() != null) {
			time = curr_Object.getTime_appointment();
			time = time.substring(0, 2);
			if (time.contains(":"))
				time = time.substring(0, 1);
			try {
				int msg3 = Integer.parseInt(time);

				if (msg3 >= 0 && msg3 < 12)
					time = "सुबेह" + msg3;
				else if (msg3 > 12 && msg3 < 16) {
					msg3 = msg3 - 12;
					time = "दोपेहेर " + msg3;
				} else if (msg3 >= 16 && msg3 <= 24) {
					msg3 = msg3 - 12;
					time = "शाम" + msg3;
				}

				logger.info(time);
			} catch (Exception e) {
				logger.error(" Time not found  ::  ", e.toString());
				System.out.println("Shaam ko 4");
			}
		}
		return new String[] { date, time };
	}

	public String validate_Number(String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("password", SwaggerProperty.password);
		map.put("id", SwaggerProperty.number_id);
		map.put("text", text.substring(5));
		map.put("model_name", "Test");

		JSONObject ApiResponse = RestTemplateHTTPMethods.PostMethod((SwaggerProperty.url + SwaggerProperty.search_regex), map);

		return ApiResponse.getString("found");
	}

	public void save_details(JSONObject new_obj) {
		try {
			CallLogs call_logs = new CallLogs();
			String cdrarray[] = new_obj.getString("cdr").split("_");
			String cdr_status = "";
			if (cdrarray.length > 2) {
				cdr_status = cdrarray[2];
			}
			String entity_intersested = new_obj.getString("entity_intersested");
			String master_call_type = new_obj.getString("call_type");
			if (master_call_type.equals("joining")) {
				entity_intersested = "joining";
			} else if (entity_intersested.equals("{entity.ask_input}")) {
				entity_intersested = null;
			} else if (entity_intersested.equals("1")) {
				entity_intersested = "interested";
			} else if (entity_intersested.equals("2")) {
				entity_intersested = "not interested";
			}
			String master_id = new_obj.getString("master_id");
			String call_start = new_obj.getString("call_start");
			String call_end = new_obj.getString("call_end");
			String cdr = get_cdr(new_obj.getString("cdr"));
			String rec_url = get_rec_url(new_obj);
			String Mobile_number = new_obj.getString("Mobile_Number");
			String sipheader = new_obj.getString("SIP_ID").split(",")[1];

			call_logs.setCdr_status(cdr_status);
			call_logs.setMaster_data_id(master_id);
			call_logs.setMobile_phone(Mobile_number);
			call_logs.setCall_triggered_at(call_start);
			call_logs.setSip_id(sipheader);
			call_logs.setCdr_seconds(cdr);
			call_logs.setCall_recording_url(rec_url);
			call_logs.setIs_interested(entity_intersested);
			call_logs.setCdr_status(cdr_status);
			call_logs.setCdr_date_time(call_end);

			callLogsRepository.save(call_logs);
			logger.info("call LogS Table updated");

			if (new_obj.getString("master_interested").equals("")) {
				MasterData masterData = masterrepo.findBymobilephone(Mobile_number);
				masterData.setIs_interested(entity_intersested);
				masterData.setRec_url(rec_url);

				masterrepo.save(masterData);
				logger.info("MasterData Table updated");
			}
		} catch (Exception e) {
			logger.info("Problem occured while storing call details into Maruti hr DATABASE Table "+e.toString());
		}

	}

	private static String get_rec_url(JSONObject call_details) {
		String rec_url = null;
		if (call_details.has("SIP_ID") && call_details.getString("SIP_ID").contains("sipheader")) {
			String sipheader = call_details.getString("SIP_ID").split(",")[1];
			sipheader = sipheader.substring(0, sipheader.length() - 1);
			logger.info("SIP_ID UPDATE Function ::: " + sipheader);
			rec_url = SwaggerProperty.rec_url + sipheader.replace(":", "_") + ".wav";
			call_details.put("rec_url", rec_url);
		}
		return rec_url;
	}

	private static String get_cdr(String cdr) {
		if (cdr.equals("") || cdr.length() < 5) {
			cdr = "~CDR_3600000_User Hung Up (exB-1)_null_ERROR: header not found";
		}
		StringBuilder ms_sb = new StringBuilder();
		for (int i = 5; i <= cdr.length(); i++) {
			if (cdr.charAt(i) != '_') {
				ms_sb.append(cdr.charAt(i));
			} else {
				break;
			}
		}
		if (ms_sb.length() > Math.pow(10, 9)) {
			return null;
		}
		int ms_int = Integer.parseInt(ms_sb.toString());

		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(ms_int),
				TimeUnit.MILLISECONDS.toMinutes(ms_int) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(ms_int) % TimeUnit.MINUTES.toSeconds(1));
		return hms;
	}

}
