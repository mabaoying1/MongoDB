package com.healthpay.iface.service;

import com.healthpay.iface.vo.*;
import com.healthpay.modules.hc.search.HpRealCardSearch;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 
 * Description:   业务处理接口
 * Filename   :   MainBusinessService.java  
 * @author    :   yyl 
 * @version   :   1.0  
 * Create at  :   2016年6月1日 下午5:56:18  
 *  
 *
 */
public interface MainBusinessService {
	
	/**
	 * 开卡申请
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:51:55
	 */
	public ResResultVo doA1001(String merid,ApplycardVo vo,String realPath);
	/**
	 * 开卡申请（带附件）
	 * @param vo
	 * @param fileList
	 * @param contextPath
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @throws FileUploadException 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1001AndFile(String merid,ApplycardVo vo, List<MultipartFile> fileList, String contextPath) throws FileUploadException, Exception;
	/**
	 * 审核结果查询
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1002(QueryIdVo vo) throws Exception;
	/**
	 * 身份认证
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1003(QueryCardVo vo) throws Exception;
	/**
	 * 支付开通备案
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1004(OpenPayVo vo) throws Exception;
	
	/**
	 * 健康卡状态查询接口
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1005(QueryIdVo vo) throws Exception;
	
	/**
	 * 资料修改接口
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1006(ApplycardUpdateVo vo) throws Exception;
	
	/**
	 * 预制卡激活接口
	 * @param vo
	 * @return
	 * @author yyl
	 * @throws Exception 
	 * @date   2016年6月1日 下午5:40:11
	 */
	public ResResultVo doA1007(QueryIdVo vo) throws Exception;

	/**
	 *
	 * @param vo
	 * @return
	 * @throws Exception
     */
	public ResResultVo doA1019(QueryCardHolderVo vo, String merid)throws Exception;
	
	/**
	 * 支付密码变更接口
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author yyl
	 * @date   2016年6月24日 下午5:11:44
	 */
	public ResResultVo doA1018(CardPasswordVo vo)throws Exception;
	
	/**
	 * 支付密码校验接口
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author yyl
	 * @date   2016年6月24日 下午5:11:44
	 */
	public ResResultVo doA1020(CardPasswordVo vo)throws Exception;
	
	/**
	 * 地区集合查询接口
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author yyl
	 * @date   2016年6月24日 下午5:11:44
	 */
	public ResResultListVo doA3001(QueryAreaVo vo)throws Exception;

	/**
	 * 实体卡绑定接口
	 * @param vo
	 * @return
	 * @throws Exception
     */
	public ResResultVo doA1021(RealCardVo vo, String merid)throws Exception;

	/**
	 * 实体卡注销
	 * @param realCardCancelVo
	 * @return
     * @throws Exception
     */
	public ReturnVo doA1022(RealCardCancelVo realCardCancelVo,boolean isSend) throws Exception;

	/**
	 * 医院列表
	 * @param
	 * @return
	 * @throws Exception
	 */
	public MerchantListVo doA1023() throws Exception;

	/**
	 * 居民已经绑定的实体健康卡
	 * @return
	 * @throws Exception
	 */
	public ResResultRealCardListVO doA1024(HpRealCardSearch search) throws Exception;


	public ReturnVo doA1025(RealCardVo vo, String merid)throws Exception;

}
