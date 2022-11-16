package ai.rezo.mshr.ManiHRBOOT.Models;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "master_data")
public class MasterData {
	
	@Id
	@GeneratedValue
	private int id;
	private int status;
	private String name;
	@Column(name = "mobile_phone")
	private String mobilephone;
	private String category;
	private Date date_appointment;
	private String time_appointment;
	private String address;
	private String last_called_at;
	private String uploaded_at;
	private String is_interested;
	private String call_type;
	private int call_count;
	private String rec_url;
	private int max_count;
	private String del_count;
	private String api_push;
	private String upload_type;
	private int include_record;
	private int scheduler_id;
	
	
	
	public MasterData() {}
	
	public MasterData(int id, int status, String name, String mobile_phone, String category, Date date_appointment,
			String time_appointment, String address, String last_called_at, String uploaded_at, String is_interested,
			String call_type, int call_count, String rec_url, int max_count, String del_count, String api_push,
			String upload_type, int include_record, int scheduler_id) {
		this.id = id;
		this.status = status;
		this.name = name;
		this.mobilephone = mobile_phone;
		this.category = category;
		this.date_appointment = date_appointment;
		this.time_appointment = time_appointment;
		this.address = address;
		this.last_called_at = last_called_at;
		this.uploaded_at = uploaded_at;
		this.is_interested = is_interested;
		this.call_type = call_type;
		this.call_count = call_count;
		this.rec_url = rec_url;
		this.max_count = max_count;
		this.del_count = del_count;
		this.api_push = api_push;
		this.upload_type = upload_type;
		this.include_record = include_record;
		this.scheduler_id = scheduler_id;
	}

	
	public String getMobile_phone() {
		return mobilephone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobilephone = mobile_phone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate_appointment() {
		return date_appointment;
	}
	public void setDate_appointment(Date date_appointment) {
		this.date_appointment = date_appointment;
	}
	public String getTime_appointment() {
		return time_appointment;
	}
	public void setTime_appointment(String time_appointment) {
		this.time_appointment = time_appointment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLast_called_at() {
		return last_called_at;
	}
	public void setLast_called_at(String last_called_at) {
		this.last_called_at = last_called_at;
	}
	public String getUploaded_at() {
		return uploaded_at;
	}
	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	public String getIs_interested() {
		return is_interested;
	}
	public void setIs_interested(String is_interested) {
		this.is_interested = is_interested;
	}
	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public int getCall_count() {
		return call_count;
	}
	public void setCall_count(int call_count) {
		this.call_count = call_count;
	}
	public String getRec_url() {
		return rec_url;
	}
	public void setRec_url(String rec_url) {
		this.rec_url = rec_url;
	}
	public int getMax_count() {
		return max_count;
	}
	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}
	public String getDel_count() {
		return del_count;
	}
	public void setDel_count(String del_count) {
		this.del_count = del_count;
	}
	public String getApi_push() {
		return api_push;
	}
	public void setApi_push(String api_push) {
		this.api_push = api_push;
	}
	public String getUpload_type() {
		return upload_type;
	}
	public void setUpload_type(String upload_type) {
		this.upload_type = upload_type;
	}
	public int getInclude_record() {
		return include_record;
	}
	public void setInclude_record(int include_record) {
		this.include_record = include_record;
	}
	public int getScheduler_id() {
		return scheduler_id;
	}
	public void setScheduler_id(int scheduler_id) {
		this.scheduler_id = scheduler_id;
	}

	
}
