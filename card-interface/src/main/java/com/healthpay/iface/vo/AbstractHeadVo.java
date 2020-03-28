package com.healthpay.iface.vo;

/**
 * 
 * Description:   json数据头
 * Filename   :   JsonHeadVo.java  
 * @author    :   yyl 
 * @version   :   1.0  
 * Create at  :   2016年5月31日 下午3:11:48  
 *  
 *
 */
public abstract class AbstractHeadVo implements BaseJsonVo {
	
	private String merid ; //商户号
	private String retcode ; //返回码
	private String errmsg ; //错误信息
	private String funcid ; //功能码
	private String timer ; //时间戳
	private String sign ; //验签码
	
	
	public String getMerid() {
		return merid;
	}
	public void setMerid(String merid) {
		this.merid = merid;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getFuncid() {
		return funcid;
	}
	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	public String getTimer() {
		return timer;
	}
	public void setTimer(String timer) {
		this.timer = timer;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
