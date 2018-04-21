package cn.com.zuul.dynamicFilter.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class ResultMsgModel implements Serializable{
	
	@NotBlank
	private Integer status_code;
	
	private String resultMsg;
	
	private Object data;
	
	private Boolean success;
	
	private String error;
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	@Override
	public String toString() {
		return "ResultMsgModel [status_code=" + status_code + ", resultMsg=" + resultMsg + ", data=" + data
				+ ", success=" + success + ", error=" + error + "]";
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResultMsgModel() {
		super();
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
