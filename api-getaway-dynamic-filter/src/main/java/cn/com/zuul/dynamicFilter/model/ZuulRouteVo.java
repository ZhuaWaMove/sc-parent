package cn.com.zuul.dynamicFilter.model;

import java.io.Serializable;

/**
 * 
 *<p>Title:ZuulRouteVo</p>
 *<p>Description:动态路由持久化到数据库类</p>
 *<p>Company:南天信息工程</p>
 * @author Acmen
 * @date 2018年1月16日 下午5:09:49
 *
 */
public class ZuulRouteVo implements Serializable{
	
	
	
	private String id;//主键id
	private String path;//路由路径配置
	private String serviceId;//服务名称
	private String url;//路由的服务地址
	/**
	 * 当stripPrefix =true (http://localhost:8080/api/user/list  -->  http://192.168.14.17:1111/user/list)
	 * 当stripPrefix = false(http://localhost:8080/api/user/list -->  http://192.168.14.17:1111/api/user/list)
	 */
	private boolean stripPrefix = true;
	private Boolean retryable; //ribbon重试机制
	private Boolean enable; //
	private String apiName;//对应的是 zuul.routes.api_name.path 这种类型
	
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isStripPrefix() {
		return stripPrefix;
	}
	public void setStripPrefix(boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}
	public Boolean getRetryable() {
		return retryable;
	}
	public void setRetryable(Boolean retryable) {
		this.retryable = retryable;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "ZuulRouteVo [id=" + id + ", path=" + path + ", serviceId=" + serviceId + ", url=" + url
				+ ", stripPrefix=" + stripPrefix + ", retryable=" + retryable + ", enable=" + enable + ", apiName="
				+ apiName + "]";
	}
	
}
