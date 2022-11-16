package ai.rezo.mshr.ManiHRBOOT.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="call_logs")
public class CallLogs {
	
	@Id
	@GeneratedValue
	private int id;
	private String master_data_id;
	private String mobile_phone;
	private String call_triggered_at;
	private String cdr_date_time;
	private String sip_id;
	private String cdr_seconds;
	private String call_recording_url;
	private int call_count;
	private String is_interested;
	private int include_record;
	private int retry_counter;
	private String cdr_status;
	private String cdr_code;
	private String cdr_cause;
	private int scheduler_id;
	public CallLogs() {}
	public CallLogs(int id, String master_data_id, String mobile_phone, String call_triggered_at, String cdr_date_time,
			String sip_id, String cdr_seconds, String call_recording_url, int call_count, String is_interested,
			int include_record, int retry_counter, String cdr_status, String cdr_code, String cdr_cause,
			int scheduler_id) {
		this.id = id;
		this.master_data_id = master_data_id;
		this.mobile_phone = mobile_phone;
		this.call_triggered_at = call_triggered_at;
		this.cdr_date_time = cdr_date_time;
		this.sip_id = sip_id;
		this.cdr_seconds = cdr_seconds;
		this.call_recording_url = call_recording_url;
		this.call_count = call_count;
		this.is_interested = is_interested;
		this.include_record = include_record;
		this.retry_counter = retry_counter;
		this.cdr_status = cdr_status;
		this.cdr_code = cdr_code;
		this.cdr_cause = cdr_cause;
		this.scheduler_id = scheduler_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaster_data_id() {
		return master_data_id;
	}
	public void setMaster_data_id(String master_data_id) {
		this.master_data_id = master_data_id;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getCall_triggered_at() {
		return call_triggered_at;
	}
	public void setCall_triggered_at(String call_triggered_at) {
		this.call_triggered_at = call_triggered_at;
	}
	public String getCdr_date_time() {
		return cdr_date_time;
	}
	public void setCdr_date_time(String cdr_date_time) {
		this.cdr_date_time = cdr_date_time;
	}
	public String getSip_id() {
		return sip_id;
	}
	public void setSip_id(String sip_id) {
		this.sip_id = sip_id;
	}
	public String getCdr_seconds() {
		return cdr_seconds;
	}
	public void setCdr_seconds(String cdr_seconds) {
		this.cdr_seconds = cdr_seconds;
	}
	public String getCall_recording_url() {
		return call_recording_url;
	}
	public void setCall_recording_url(String call_recording_url) {
		this.call_recording_url = call_recording_url;
	}
	public int getCall_count() {
		return call_count;
	}
	public void setCall_count(int call_count) {
		this.call_count = call_count;
	}
	public String getIs_interested() {
		return is_interested;
	}
	public void setIs_interested(String is_interested) {
		this.is_interested = is_interested;
	}
	public int getInclude_record() {
		return include_record;
	}
	public void setInclude_record(int include_record) {
		this.include_record = include_record;
	}
	public int getRetry_counter() {
		return retry_counter;
	}
	public void setRetry_counter(int retry_counter) {
		this.retry_counter = retry_counter;
	}
	public String getCdr_status() {
		return cdr_status;
	}
	public void setCdr_status(String cdr_status) {
		this.cdr_status = cdr_status;
	}
	public String getCdr_code() {
		return cdr_code;
	}
	public void setCdr_code(String cdr_code) {
		this.cdr_code = cdr_code;
	}
	public String getCdr_cause() {
		return cdr_cause;
	}
	public void setCdr_cause(String cdr_cause) {
		this.cdr_cause = cdr_cause;
	}
	public int getScheduler_id() {
		return scheduler_id;
	}
	public void setScheduler_id(int scheduler_id) {
		this.scheduler_id = scheduler_id;
	}
	
	
	
	
}
