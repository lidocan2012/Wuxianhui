package com.wifihi.terminalServerAction;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.wifihi.terminalServerService.PushMessageResult;

public class PushMessageAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	//¼«¹âAPPKEY
	private static final String appKey="6f45844d5384e36ce6c5ca8f";
	private static final String masterSecret="2e6130b3f092865d5812067c";
	private static int timeToLive =  60 * 60 * 24; 
	public static final String ALERT = "¼øÈ¨";
	public static JPushClient jpush= null;
	
	private String reqContent = null;
	private PushMessageResult pushMessageResult;

	
	public PushMessageResult getPushMessageResult() {
		return pushMessageResult;
	}

	public void setPushMessageResult(PushMessageResult pushMessageResult) {
		this.pushMessageResult = pushMessageResult;
	}

	public void setServletRequest(HttpServletRequest arg0) {   
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	
	public String  PushMessage() throws APIConnectionException, APIRequestException{
		JSONObject json = new JSONObject(this.reqContent);
		jpush = new JPushClient(masterSecret, appKey, timeToLive); 
		String mID = json.getString("messageID");
		PushPayload payload = buildPushObject_all_all_alert(mID);
		PushResult result = jpush.sendPush(payload);
		pushMessageResult.setPushResult("OK");
		System.out.println(result);
		return SUCCESS;
	}
	
	public static PushPayload buildPushObject_all_all_alert(String registrationId) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.registrationId(registrationId))
				.setNotification(Notification.alert(ALERT))
				.build();
	}
}
