package com.healthpay.iface.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.healthpay.common.Constant;
import com.healthpay.common.config.Global;
import com.healthpay.common.exception.BusException;
import com.healthpay.common.persistence.Page;
import com.healthpay.common.utils.DbUtil;
import com.healthpay.common.utils.IdGen;
import com.healthpay.common.utils.MyBeanUtils;
import com.healthpay.iface.service.MainBusinessService;
import com.healthpay.iface.util.StringUtils;
import com.healthpay.iface.vo.A1024VO;
import com.healthpay.iface.vo.ApplycardUpdateVo;
import com.healthpay.iface.vo.ApplycardVo;
import com.healthpay.iface.vo.CardPasswordVo;
import com.healthpay.iface.vo.MerchantListVo;
import com.healthpay.iface.vo.MerchantVo;
import com.healthpay.iface.vo.OpenPayVo;
import com.healthpay.iface.vo.QueryAreaVo;
import com.healthpay.iface.vo.QueryCardHolderVo;
import com.healthpay.iface.vo.QueryCardVo;
import com.healthpay.iface.vo.QueryIdVo;
import com.healthpay.iface.vo.RealCardCancelVo;
import com.healthpay.iface.vo.RealCardVo;
import com.healthpay.iface.vo.ResResultAreaVo;
import com.healthpay.iface.vo.ResResultCardHolderVo;
import com.healthpay.iface.vo.ResResultListVo;
import com.healthpay.iface.vo.ResResultRealCardListVO;
import com.healthpay.iface.vo.ResResultVo;
import com.healthpay.iface.vo.ReturnVo;
import com.healthpay.modules.hc.entity.HpApplycard;
import com.healthpay.modules.hc.entity.HpApplycardHis;
import com.healthpay.modules.hc.entity.HpCardaccount;
import com.healthpay.modules.hc.entity.HpCardholder;
import com.healthpay.modules.hc.entity.HpHealthcard;
import com.healthpay.modules.hc.entity.HpMerCardlist;
import com.healthpay.modules.hc.entity.HpRealCard;
import com.healthpay.modules.hc.search.HpRealCardSearch;
import com.healthpay.modules.hc.service.HpApplycardHisService;
import com.healthpay.modules.hc.service.HpApplycardService;
import com.healthpay.modules.hc.service.HpCardaccountService;
import com.healthpay.modules.hc.service.HpCardholderService;
import com.healthpay.modules.hc.service.HpHealthcardService;
import com.healthpay.modules.hc.service.HpMerCardlistService;
import com.healthpay.modules.hc.service.HpRealCardService;
import com.healthpay.modules.iface.entity.HpIfaceAddress;
import com.healthpay.modules.iface.entity.HpIfaceMerchant;
import com.healthpay.modules.iface.entity.HpIfaceMsgqueue;
import com.healthpay.modules.iface.service.HpIfaceAddressService;
import com.healthpay.modules.iface.service.HpIfaceMerchantService;
import com.healthpay.modules.iface.service.HpIfaceMsgqueueService;
import com.healthpay.modules.sys.entity.Area;
import com.healthpay.modules.sys.entity.AreaBean;
import com.healthpay.modules.sys.entity.Office;
import com.healthpay.modules.sys.entity.User;
import com.healthpay.modules.sys.service.AreaService;
import com.healthpay.modules.sys.service.OfficeService;
import com.healthpay.modules.sys.utils.AreaUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Description: 业务处理实现类 Filename : MainBusinessServiceImpl.java
 *
 * @author : yyl
 * @version : 1.0 Create at : 2016年6月1日 下午5:57:19
 */
@Service("mainBusinessService")
public class MainBusinessServiceImpl implements MainBusinessService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HpIfaceMsgqueueService hpIfaceMsgqueueService;
	@Autowired
	private HpMerCardlistService hpMerCardlistService;
	@Autowired
	private HpApplycardService hpApplycardService;
	@Autowired
	private HpApplycardHisService hpApplycardHisService;
	@Autowired
	private HpCardholderService hpCardholderService;
	@Autowired
	private HpHealthcardService hpHealthcardService;
	@Autowired
	private HpCardaccountService hpCardaccountService;
	@Autowired
	private HpRealCardService hpRealCardService;
	@Autowired
	private HpIfaceMerchantService hpIfaceMerchantService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private HpIfaceAddressService hpIfaceAddressService;

	@Transactional
	@Override
	public ResResultVo doA1001(String merid, ApplycardVo vo, String realPath) {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		String status = Constant.IfaceStatus.SUCCESS;
		String reason = "";
		String pkid = null;//电子健康卡id

		// 先查询是否允许开卡
		ResResultVo resultVo = this.doA1005(nationality, docuType, docuId);
		//未申请开卡
		if (Constant.IfaceStatus.NONE.equals(resultVo.getStatus())) {
			try {

				HpApplycard applycard = new HpApplycard();
				MyBeanUtils.copyBeanNotNull2Bean(vo, applycard);//数据对象空值不拷贝到目标对象
				applycard.setStr00(merid);// 申请商户
				//证件类型+证件号码（唯一）
				applycard.setIdentityId(docuType + docuId);

				Office office = officeService.get("2");//获取机构 默认发卡机构为中山卫生局
				// FIXME 发卡机构
				applycard.setOffice(office);

				String code = "";//居住地
				if (StringUtils.isNotNull(applycard.getVillagecode2())) {// 村代码
					code = applycard.getVillagecode2();
				} else if (StringUtils.isNotNull(applycard.getTowncode2())) {// 镇代码
					code = applycard.getTowncode2();
				} else if (StringUtils.isNotNull(applycard.getCountycode2())) {// 县级代码
					code = applycard.getCountycode2();
				} else if (StringUtils.isNotNull(applycard.getCitycode2())) {// 市级代码
					code = applycard.getCitycode2();
				} else if (StringUtils.isNotNull(applycard.getProvcode2())) {// 省级代码
					code = applycard.getProvcode2();
				}

				//身份证图片地址
				if (StringUtils.isNotEmpty(vo.getImg1())) {
					applycard.setUrl0(writeToFile(vo.getImg1(), merid, realPath));
				}
				if (StringUtils.isNotEmpty(vo.getImg2())) {
					applycard.setUrl1(writeToFile(vo.getImg2(), merid, realPath));
				}
				if (StringUtils.isNotNull(code)) {
					applycard.setArea1(areaService.getByCode(code));
				}

				code = null;  //户籍地
				if (StringUtils.isNotNull(applycard.getVillagecode())) {
					code = applycard.getVillagecode();
				} else if (StringUtils.isNotNull(applycard.getTowncode())) {
					code = applycard.getTowncode();
				} else if (StringUtils.isNotNull(applycard.getCountycode())) {
					code = applycard.getCountycode();
				} else if (StringUtils.isNotNull(applycard.getCitycode())) {
					code = applycard.getCitycode();
				} else if (StringUtils.isNotNull(applycard.getProvcode())) {
					code = applycard.getProvcode();
				}
				if (StringUtils.isNotNull(code)) {
					applycard.setArea(areaService.getByCode(code));
				}

				User createBy = new User();
				createBy.setId("1");//默认系统管理员
				applycard.setCreateBy(createBy);
				applycard.setCreateDate(DbUtil.getDate());

				// setHpApplycard(applycard);
				// 查询商户信息
				HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(merid);
				if (hpIfaceMerchant == null) {
					hpIfaceMerchant = hpIfaceMerchantService.get(merid);
				}

				if (hpIfaceMerchant == null) {
					throw new BusException("商户" + merid + "不存在");
				}
				applycard.setSource(Long.valueOf(hpIfaceMerchant.getSource().toString()));//数据来源
				// 保存健康卡申请记录
				hpApplycardService.save(applycard);

				status = Constant.IfaceStatus.WAITAUDIT; // 待审核
				boolean syncflag = StringUtils.isNotEmpty(vo.getIcCardId());// 实体卡卡号是否为空

				// 自动审核
				if (hpIfaceMerchant.getIsAutoAudit()) {
					pkid = hpApplycardService.audit(applycard, "1", !syncflag); // 审核通过
					status = Constant.IfaceStatus.AUDIT; // 审核通过
				}

				// 如果实体卡卡号不为空且审核通过
				if (syncflag && StringUtils.isNotEmpty(pkid)) {
					Integer type = 0;
					if (null != vo.getType())
						type = vo.getType().intValue();
					RealCardVo realCardVo = new RealCardVo();
					realCardVo.setIcCardId(vo.getIcCardId());
					realCardVo.setType(type);
					realCardVo.setHealthCardId(pkid);
					realCardVo.setDocuId(vo.getDocuId());
					realCardVo.setDocuType(vo.getDocuType());
					realCardVo.setNationality(vo.getNationality());
					// 调用实体卡绑定接口
					this.doA1021(realCardVo, merid);
				}
				resultVo.setHealthCardId(pkid);
				resultVo.setIcCardId(vo.getIcCardId());
				reason = "";
			} catch (BusException e) {
				throw e;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new BusException("提交申请失败");
			}
			resultVo.setStatus(status);
			resultVo.setReason(reason);
		}
		return resultVo;
	}

	/**
	 * 属性赋值
	 *
	 * @param hpApplycard
	 * @author yyl
	 * @date 2016年5月27日 上午11:07:37
	 */
	private void setHpApplycard(HpApplycard hpApplycard) {
		if (StringUtils.isBlank(hpApplycard.getCardId())) {
			hpApplycard.setCardId("111");
		}

		if (hpApplycard.getSource() == null) {
			hpApplycard.setSource(1L);
		}

		// User user = UserUtils.getUser();
		//
		// if (null != user) {
		// hpApplycard.setOffice(user.getCompany());
		// }

		if (null != hpApplycard.getArea1() && StringUtils.isNotBlank(hpApplycard.getArea1().getId())) {
			Area area = this.areaService.get(hpApplycard.getArea1());
			String[][] areas = getAreas(area, new String[6][2]);

			hpApplycard.setCountrycode2(areas[0][0]);
			hpApplycard.setCountryname2(areas[0][1]);
			hpApplycard.setProvcode2(areas[1][0]);
			hpApplycard.setProvname2(areas[1][1]);
			hpApplycard.setCitycode2(areas[2][0]);
			hpApplycard.setCityname2(areas[2][1]);
			hpApplycard.setCountycode2(areas[3][0]);
			hpApplycard.setCountyname2(areas[3][1]);
			hpApplycard.setTowncode2(areas[4][0]);
			hpApplycard.setTownname2(areas[4][1]);
			hpApplycard.setVillagecode2(areas[5][0]);
			hpApplycard.setVillagename2(areas[5][1]);
		}

		if (null != hpApplycard.getArea() && StringUtils.isNotBlank(hpApplycard.getArea().getId())) {
			Area area = this.areaService.get(hpApplycard.getArea().getId());
			int length = Integer.parseInt(area.getType());
			String[][] areas = getAreas(area, new String[6][2]);

			hpApplycard.setCountrycode(areas[0][0]);
			hpApplycard.setCountryname(areas[0][1]);
			hpApplycard.setProvcode(areas[1][0]);
			hpApplycard.setProvname(areas[1][1]);
			hpApplycard.setCitycode(areas[2][0]);
			hpApplycard.setCityname(areas[2][1]);
			hpApplycard.setCountycode(areas[3][0]);
			hpApplycard.setCountyname(areas[3][1]);
			hpApplycard.setTowncode(areas[4][0]);
			hpApplycard.setTownname(areas[4][1]);
			hpApplycard.setVillagecode(areas[5][0]);
			hpApplycard.setVillagename(areas[5][1]);
		}
	}

	/**
	 * 递归获取区域数据
	 *
	 * @param area
	 * @param areas
	 * @return
	 * @author yyl
	 * @date 2016年5月27日 上午11:49:07
	 */
	private String[][] getAreas(Area area, String[][] areas) {
		int x = Integer.valueOf(area.getType()) - 1;

		if (Constant.AREA_TYPE_6.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		}
		if (Constant.AREA_TYPE_5.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_4.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_3.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_2.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		} else if (Constant.AREA_TYPE_1.equals(area.getType())) {
			areas[x][0] = area.getCode();
			areas[x][1] = area.getName();
		}

		if (StringUtils.isNotNull(area.getParentId())) {
			Area area2 = areaService.get(area.getParentId());
			if (null != area2) {
				getAreas(area2, areas);
			}
		}

		return areas;
	}

	@Override
	public ResResultVo doA1001AndFile(String merid, ApplycardVo vo, List<MultipartFile> fileList, String contextPath)
			throws FileUploadException, Exception {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		String status = Constant.IfaceStatus.FAILURE;
		String reason = "";
		// 先查询是否允许开卡
		ResResultVo resultVo = this.doA1005(nationality, docuType, docuId);
		if (Constant.IfaceStatus.NONE.equals(resultVo.getStatus())) {
			try {
				HpApplycard applycard = new HpApplycard();
				MyBeanUtils.copyBeanNotNull2Bean(vo, applycard);
				// List<String> list = writeToFile(, vo.getMerid()
				// ,contextPath);
				applycard.setFlag00(1L);
				// applycard.setUrl0(list.get(0));
				// applycard.setUrl1(list.get(1));
				applycard.setStr00(merid);// 保存商戶號
				applycard.setIdentityId(nationality + String.valueOf(docuType) + docuId);
				Office office = officeService.get("2");
				applycard.setOffice(office);
				String code = "";
				if (StringUtils.isNotNull(applycard.getVillagecode2())) {
					code = applycard.getVillagecode2();
				} else if (StringUtils.isNotNull(applycard.getTowncode2())) {
					code = applycard.getTowncode2();
				} else if (StringUtils.isNotNull(applycard.getCountycode2())) {
					code = applycard.getCountycode2();
				} else if (StringUtils.isNotNull(applycard.getCitycode2())) {
					code = applycard.getCitycode2();
				} else if (StringUtils.isNotNull(applycard.getProvcode2())) {
					code = applycard.getProvcode2();
				}
				Area area = areaService.getByCode(code);
				if (null == area) {
					area = new Area();
					area.setId("1");
				}
				applycard.setArea1(area);
				User createBy = new User();
				createBy.setId("1");
				applycard.setCreateBy(createBy);
				applycard.setCreateDate(DbUtil.getDate());
				hpApplycardService.save(applycard);
				status = Constant.IfaceStatus.SUCCESS;
				reason = "";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				status = Constant.IfaceStatus.FAILURE;
				reason = "提交申请失败";
			}
			resultVo.setStatus(status);
			resultVo.setReason(reason);
		}
		return resultVo;
	}

	@Override
	public ResResultVo doA1002(QueryIdVo vo) throws Exception {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		String healthCardId = "";
		String reason = "";
		String status = Constant.IfaceStatus.NONE;
		String identityId = nationality + String.valueOf(docuType) + docuId;
		HpCardholder bean1 = hpCardholderService.get(identityId);
		if (bean1 != null && bean1.getStatus() != null && bean1.getStatus().intValue() == 1) {
			status = Constant.IfaceStatus.AUDIT;
			HpHealthcard hpHealthcard = hpHealthcardService.getByIdentityIdAndStatus(identityId, "2");
			if (null != hpHealthcard) {
				healthCardId = hpHealthcard.getPkid();
			}
		} else {
			HpApplycardHis hpApplycardHis = new HpApplycardHis();
			hpApplycardHis.setIdentityId(identityId);
			Page<HpApplycardHis> page = new Page<HpApplycardHis>();
			page.setOrderBy("a.create_date desc");
			hpApplycardHis.setPage(page);
			Page<HpApplycardHis> page1 = hpApplycardHisService.findPage(page, hpApplycardHis);
			if (page1 != null && page1.getList() != null && !page1.getList().isEmpty()) {
				status = Constant.IfaceStatus.REFUSE;
				HpApplycardHis applycardHis = page.getList().get(0);
				reason = applycardHis.getReason();
			} else {
				HpApplycard bean2 = hpApplycardService.getByIdentityId(identityId);
				if (null != bean2)
					status = Constant.IfaceStatus.APPLIED;
			}

		}
		return new ResResultVo(nationality, docuType, docuId, healthCardId, null, null, status, reason);
	}

	@Override
	public ResResultVo doA1003(QueryCardVo vo) throws Exception {
		String healthCardId = vo.getHealthCardId();
		return doA1003(healthCardId);
	}

	@Override
	public ResResultVo doA1004(OpenPayVo vo) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String status = Constant.IfaceStatus.SUCCESS;
		String reason = "";
		// 先身份认证
		ResResultVo resultVo = doA1003(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (card.getIsOpenpay() != null && card.getIsOpenpay().intValue() == 1) {
				status = Constant.IfaceStatus.FAILURE;
				reason = "支付功能已经开通";
			} else {
				card.setIsOpenpay(1L);
				Date date = DbUtil.getDate();
				if (null == card.getFirstOpenpayDate()) {
					card.setFirstOpenpayDate(date);
				}
				card.setOpenpayDate(date);
				card.setPaylimit(vo.getPaylimit());
				card.setPassword(vo.getPassword());
				card.setIsPwdfree(vo.getIsPwdfree());
				card.setSmalllimit(vo.getSmalllimit());
				card.setReserved(vo.getReserved());
				hpHealthcardService.update(card);
				// 添加卡帐关系
				HpCardaccount cardaccount = new HpCardaccount();
				cardaccount.setCardPkid(card.getPkid());
				cardaccount.setCardId(card.getCardId());
				cardaccount.setPaysystem(vo.getPaysystem());
				cardaccount.setAccount(vo.getAccount());
				cardaccount.setAccountname(vo.getAccountname());
				cardaccount.setBankid(vo.getBankid());
				cardaccount.setBankname(vo.getBankname());
				hpCardaccountService.save(cardaccount);
				status = Constant.IfaceStatus.SUCCESS;
				reason = "";
			}

		} else {
			status = Constant.IfaceStatus.FAILURE;
			reason = "身份认证失败";
		}

		resultVo.setStatus(status);
		resultVo.setReason(reason);
		return resultVo;
	}

	/**
	 * 写入传输过来的文件
	 *
	 * @param img
	 * @param merid
	 * @return
	 * @throws FileUploadException
	 * @throws Exception
	 * @author yyl
	 * @date 2016年6月1日 下午5:25:52
	 */
	private String writeToFile(String img, String merid, String contextPath) throws FileUploadException, Exception {
		String realPath = Global.IFACEFILES_BASE_URL + File.separator + merid + "/images/";
		BASE64Encoder encode = new BASE64Encoder();
		String base64 = encode.encode(img.getBytes());
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = decode.decodeBuffer(base64);
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		BufferedImage bi1 = ImageIO.read(bais);
		String fileName = IdGen.uuid() + ".bmp";
		File w2 = new File(contextPath + realPath + fileName);// 可以是jpg,png,gif格式
		ImageIO.write(bi1, "jpg", w2);
		return realPath + fileName;
	}

	/**
	 * 身份认证
	 *
	 * @param healthCardId
	 * @return
	 * @author yyl
	 * @date 2016年6月2日 上午10:45:20
	 */
	private ResResultVo doA1003(String healthCardId) {
		String status = Constant.IfaceStatus.FAILURE;
		String reason = "";
		HpHealthcard card = hpHealthcardService.get(healthCardId);
		if (null == card) {
			status = Constant.IfaceStatus.FAILURE;
			reason = "健康卡卡号错误";
		} else {
			if (StringUtils.isNotNull(card.getStatus()) && "2".equals(card.getStatus())) {
				status = Constant.IfaceStatus.SUCCESS;
			} else {
				status = Constant.IfaceStatus.FAILURE;
				reason = "健康卡未激活或已注销";
			}
		}
		return new ResResultVo(healthCardId, status, reason);
	}

	@Override
	public ResResultVo doA1005(QueryIdVo vo) throws Exception {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		return this.doA1005(nationality, docuType, docuId);
	}

	/**
	 * 开卡查询
	 *
	 * @param nationality
	 * @param docuType
	 * @param docuId
	 * @return
	 * @author yyl
	 * @date 2016年6月3日 上午10:35:52
	 */
	public ResResultVo doA1005(String nationality, String docuType, String docuId) {
		String status = Constant.IfaceStatus.NONE;
		String reason = "";
		String healthCardId = "";
		String icCardId = "";
		Long type = null;
		// 查询持卡人档案信息（证件类型+证件号码）
		String identityId = docuType + docuId;
		HpCardholder holder = hpCardholderService.get(identityId);
		// 判断是否有持卡人档案档案信息,没有再判断是否有申请记录
		if (null == holder) {
			// 查询健康卡申请记录（证件类型+证件号码）
			HpApplycard applycard =hpApplycardService.getByIdentityId(identityId);
			// 判断是否有健康卡申请记录
			if (null == applycard) {
				status = Constant.IfaceStatus.NONE;
				reason = "未申请";
			} else {
				status = Constant.IfaceStatus.APPLIED;
				reason = "该有效身份证已提交申请";
			}
		} else {
			if (holder.getStatus() != null && holder.getStatus().intValue() == 0) { // 已销户
				status = Constant.IfaceStatus.INVALID;
				reason = "已销户";
			} else {
				// 查询电子健康卡记录（身份证号码）
				HpHealthcard card = hpHealthcardService.getByIdentityId(identityId);
				if (card != null && "1".equals(card.getStatus())) {// 待激活
					healthCardId = card.getPkid();
					status = Constant.IfaceStatus.ACTIVATE;
					reason = "该有效身份证已开卡,待激活";
				} else if (card != null && "2".equals(card.getStatus())) {// 生效（审核通过）
					healthCardId = card.getPkid();
					status = Constant.IfaceStatus.OPEDCARD;
					reason = "该有效身份证已开卡";
				} else {
					status = Constant.IfaceStatus.NONE;
					reason = "未申请";
				}
				if (StringUtils.isNotEmpty(healthCardId)) {
					// 查询实体卡记录（健康卡id+健康卡实体卡类型）
					HpRealCard realCard = hpRealCardService.getIcCardId(healthCardId, 1, 0);
					if (null == realCard)
						// 查询实体卡记录（健康卡id+社保卡类型）
						realCard = hpRealCardService.getIcCardId(healthCardId, 1, 1);
					if (null != realCard) {
						icCardId = realCard.getIccardid();
						type = realCard.getType();
					}
				}
			}
		}
		return new ResResultVo(nationality, docuType, docuId, healthCardId, icCardId, type, status, reason);
	}
	
	/**
	 * 料修改接口（商户卡管）
	 */
	@Override
	public ResResultVo doA1006(ApplycardUpdateVo vo) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String status = Constant.IfaceStatus.SUCCESS;
		String reason = "";
		// 先身份认证
		ResResultVo resultVo = doA1003(healthCardId);
		if (!Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			throw new BusException(resultVo.getReason());
		}
		try {
			// 获取健康卡记录
			HpHealthcard hpHealthcard = hpHealthcardService.get(healthCardId);
			if (null == hpHealthcard) {
				throw new BusException("健康卡卡号错误");
			}
			// 获取持卡人记录
			HpCardholder hpCardholder = hpCardholderService.get(hpHealthcard.getIdentityId());
			// 每次地址都需要更新,所以先把地址相关字段都拷贝出来。
			AreaBean areaBean = new AreaBean();

			// 把orig和dest相同属性的value复制到dest中
			MyBeanUtils.copyBean2Bean(areaBean, vo);
			// 持卡人信息 对象拷贝,数据对象空值不拷贝到目标对象
			MyBeanUtils.copyBeanNotNull2Bean(vo, hpCardholder);

			// 卡数据信息
			if (StringUtils.isNoneBlank(vo.getProvname2()) || StringUtils.isNoneBlank(vo.getCityname2())) {
				MyBeanUtils.copyBean2Bean(hpCardholder, areaBean);// 覆盖原住址信息
				MyBeanUtils.copyBean2Bean(hpHealthcard, areaBean);// 覆盖原住址信息
			}
			// 更新持卡人信息
			hpCardholderService.update(hpCardholder);
			// 更新健康卡信息
			hpHealthcardService.update(hpHealthcard);
			// --------------------银联异步-------------------
			// 把传送给网银的修改资料记录写入队列表
			// HpIfaceMerchant gateway =
			// hpIfaceMerchantService.getGateway("支付网关",99);
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1007");
			// hpIfaceMsgqueue.setIcCardId("");
			// hpIfaceMsgqueue.setDocuid(hpCardholder.getDocuId());
			// hpIfaceMsgqueue.setDocutype(hpCardholder.getDocuType());
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusException("健康卡资料修改失败");
		}
		return null;
	}

	@Override
	public ResResultVo doA1007(QueryIdVo vo) throws Exception {
		String nationality = vo.getNationality();
		String docuType = vo.getDocuType();
		String docuId = vo.getDocuId();
		String status = Constant.IfaceStatus.FAILURE;
		String reason = "";
		String identityId = nationality + String.valueOf(docuType) + docuId;
		HpHealthcard card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "1"); // 待激活
		// 、先默认只有一个吧
		if (card != null) {
			try {
				card.setStatus("2");
				hpHealthcardService.update(card);
				status = Constant.IfaceStatus.SUCCESS;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				status = Constant.IfaceStatus.FAILURE;
			}
		}
		return new ResResultVo(nationality, docuType, docuId, status, reason);
	}

	/**
	 * 查询健康卡资料信息
	 */
	@Override
	public ResResultVo doA1019(QueryCardHolderVo vo, String merid) throws Exception {
		String nationality = vo.getNationality();//国籍
		String docuType = vo.getDocuType();//证件类型  (国籍不为空时，不能为空)
		String docuId = vo.getDocuId();//证件号码(国籍,证件类型不为空时，不能为空)
		String healthCardId = vo.getHealthCardId();//健康e卡号（虚拟卡号）  (国籍、证件类型、证件号码为空时不能为空)
		String icCardId = vo.getIcCardId();//实体卡卡号(国籍、证件类型、证件号码、健康e卡号为空时不能为空)
		Integer type = vo.getType();//实体卡类型 (0 居民健康卡  1 社保卡（社保卡的参保号 前8位作为实体卡卡号）)
		String reason = "";
		HpHealthcard card = null;
		HpCardholder cardholder = null;
		HpRealCard hpRealCard = null;
		if ((StringUtils.isEmpty(nationality) || StringUtils.isEmpty(docuId) || null == docuType)) {
			if (StringUtils.isEmpty(healthCardId) && StringUtils.isEmpty(icCardId)) {
				reason = "(国籍，证件类型，证件号码)或健康卡号或实体卡卡号不能同时为空";
				throw new BusException(reason);
			} else {
				if (!(StringUtils.isEmpty(nationality) && StringUtils.isEmpty(docuId) && null == docuType)) {
					reason = "(国籍，证件类型，证件号码)全为空或全不为空";
					throw new BusException(reason);
				}
			}
		}
		
		// 1.如果虚拟卡卡号不为空，根据虚拟卡号查询实体卡信息
		if (StringUtils.isNotEmpty(healthCardId)) {
			card = hpHealthcardService.get(healthCardId);
			if (null != card) {
				cardholder = hpCardholderService.get(card.getIdentityId());

				hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 0L);
				if (null == hpRealCard) {
					hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 1L);
				}
			} else {
				reason = "健康卡卡号错误";
				throw new BusException(reason);
			}
		}
		// 2.如果实体卡号不为空，查出实体卡信息；通过证件号查出持卡者信息，通过证件查出健康卡信息
		else if (StringUtils.isNotEmpty(icCardId)) {
			if (null == type) {
				reason = "实体卡类型不能为空";
				throw new BusException(reason);
			}
			if (type == 0) {
				hpRealCard = hpRealCardService.getRealCard(icCardId, type);
			}
			if (type == 1) {
				hpRealCard = hpRealCardService.getHealthCardIdNotCancel(icCardId, type);
			}
			// 查不出实体卡
			if (null == hpRealCard) {
				if (StringUtils.isEmpty(docuId)) {
					reason = "不存在的实体卡卡号";
					throw new BusException(reason);
				} else {
					cardholder = hpCardholderService.getByNationAndDocuTypeAndDocuId(nationality, docuType, docuId);
					String identityId = nationality + String.valueOf(docuType) + docuId;
					card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "2");// 已激活
					healthCardId = card.getPkid();
					if (null == card || null == cardholder) {
						reason = "没有找到有效的健康卡信息";
						throw new BusException(reason);
					}
				}
			}
			// 查出实体卡
			else {
				if (0 == hpRealCard.getStatus().intValue()) {
					reason = "该实体卡卡号已注销";
					throw new BusException(reason);
				}
				if (2 == hpRealCard.getStatus().intValue()) {
					reason = "该实体卡卡号已挂失";
					throw new BusException(reason);
				}
				card = hpHealthcardService.get(hpRealCard.getCardPkid());
				cardholder = hpCardholderService.get(card.getIdentityId());
				healthCardId = card.getPkid();
				// 首先判断商户类型是否为HIS
				HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(merid);
				if (null != hpIfaceMerchant && 1 == hpIfaceMerchant.getSource().intValue()) {
					if (null == hpMerCardlistService.getpkId(merid, hpRealCard.getIccardid())) {
						// 写HIS系统绑定记录
						HpMerCardlist hpMerCardlist = new HpMerCardlist();
						hpMerCardlist.setMerId(merid);
						hpMerCardlist.setIccardId(hpRealCard.getIccardid());
						hpMerCardlist.setHealthCardId(healthCardId);
						hpMerCardlist.setDate0(new Date());
						// flag0：1为第一次创建实体卡的标记，2为通过1019接口访问实体卡的标记
						hpMerCardlist.setFlag0(2);
						hpMerCardlistService.save(hpMerCardlist);
					}
				}
			}

		}
		// 3.健康卡、实体卡号都为空时，通过证件查出持卡者信息，通过证件查出健康卡信息
		else {
			cardholder = hpCardholderService.getByNationAndDocuTypeAndDocuId(nationality, docuType, docuId);
			String identityId = nationality + String.valueOf(docuType) + docuId;
			card = hpHealthcardService.getByIdentityIdAndStatus(identityId, "2");// 已激活
			if (null == card || null == cardholder) {
				reason = "没有找到有效的健康卡信息";
				throw new BusException(reason);
			}
			healthCardId = card.getPkid();
			hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 0L);
			if (null == hpRealCard) {
				hpRealCard = hpRealCardService.getIcCardId(card.getPkid(), 1L, 1L);
			}
		}
		ResResultCardHolderVo ret = new ResResultCardHolderVo();
		ret.setNationality(nationality);
		ret.setDocuId(docuId);
		ret.setDocuType(docuType);
		if (null != hpRealCard) {
			ret.setIcCardId(hpRealCard.getIccardid());
			ret.setType(hpRealCard.getType());
		}
		if (null != card) {
			MyBeanUtils.copyBeanNotNull2Bean(card, ret);
		}
		if (null != cardholder) {
			MyBeanUtils.copyBeanNotNull2Bean(cardholder, ret);
		}
		ret.setHealthCardId(healthCardId);
		return ret;
	}

	@Override
	public ResResultVo doA1018(CardPasswordVo vo) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String status = Constant.IfaceStatus.FAILURE;
		// 先身份认证
		ResResultVo resultVo = doA1003(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			card.setPassword(vo.getPassword());
			hpHealthcardService.update(card);
			status = Constant.IfaceStatus.SUCCESS;
		}
		resultVo.setStatus(status);
		return resultVo;
	}

	@Override
	public ResResultVo doA1020(CardPasswordVo vo) throws Exception {
		String healthCardId = vo.getHealthCardId();
		String password = vo.getPassword();
		String status = Constant.IfaceStatus.FAILURE;
		
		// 先身份认证
		ResResultVo resultVo = doA1003(healthCardId);
		if (Constant.IfaceStatus.SUCCESS.equals(resultVo.getStatus())) {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (password.equals(card.getPassword())) {
				status = Constant.IfaceStatus.SUCCESS;
			}
		}
		resultVo.setStatus(status);
		return resultVo;
	}

	@Override
	public ResResultListVo doA3001(QueryAreaVo vo) throws Exception {
		String code = vo.getCode();
		if (StringUtils.isNull(code)) {
			code = "0"; // 默认查询中国的子集
		}
		Area area = AreaUtils.getArea(code);
		String parentId = "";
		if (area != null) {
			parentId = area.getId();
		}
		List<Area> list = AreaUtils.getAreaListByParentId(parentId);
		List<ResResultAreaVo> resList = Lists.newArrayList();
		for (Area bean : list) {
			ResResultAreaVo resVo = new ResResultAreaVo();
			resVo.setCode(bean.getCode());
			resVo.setName(bean.getName());
			resVo.setLevel(bean.getType());
			resList.add(resVo);
		}
		ResResultListVo retVo = new ResResultListVo();
		retVo.setMx(resList);
		return retVo;
	}

	/**
	 * 实体卡绑定
	 */
	@Transactional
	@Override
	public ResResultVo doA1021(RealCardVo vo, String merid) throws Exception {
		HpRealCard hpRealCard = null;
		// 判断是否绑定过社保卡和健康卡实体卡（状态不为0）
		if (vo.getType() == 1) {
			hpRealCard = hpRealCardService.getHealthCardIdNotCancel(vo.getIcCardId(), vo.getType());
		}
		// 根据实体卡号和类型查询所有状态实体卡
		if (vo.getType() == 0) {
			hpRealCard = hpRealCardService.getRealCard(vo.getIcCardId(), vo.getType());
		}
		if (null != hpRealCard) {
			throw new BusException("该实体卡号已绑定虚拟卡");
		}
		// 先查询有效的实体卡卡号
		HpRealCard realCard = hpRealCardService.getIcCardNotCancel(vo.getHealthCardId(), vo.getType());
		if (null != realCard) {
			if (realCard.getIccardid().equals(vo.getIcCardId())) {
				throw new BusException("该实体卡已绑定");
			}
			// 注销以前的实体卡
			RealCardCancelVo realCardCancelVo = new RealCardCancelVo();
			MyBeanUtils.copyBeanNotNull2Bean(vo, realCardCancelVo);
			realCardCancelVo.setIcCardId(realCard.getIccardid());
			realCardCancelVo.setOptType(0);// 操作类型 0 注销 1 挂失 2 解除挂失
			this.doA1022(realCardCancelVo, false);
		}

		hpRealCard = new HpRealCard();
		hpRealCard.setCardPkid(vo.getHealthCardId());
		hpRealCard.setType(Long.valueOf(vo.getType().toString()));
		hpRealCard.setIccardid(vo.getIcCardId());
		// hpRealCard.setType(0L);
		hpRealCard.setStatus(1L);
		hpRealCard.setIsNewRecord(true);
		hpRealCard.setStr03(merid);// str03存放商户id
		hpRealCardService.save(hpRealCard);

		// 记录绑定实体卡的医院信息
		// 首先判断商户类型是否为HIS
		HpIfaceMerchant hpIfaceMerchant = hpIfaceMerchantService.getFormCache(merid);
		if (null != hpIfaceMerchant && 1 == hpIfaceMerchant.getSource().intValue()) {
			if (null == hpMerCardlistService.getpkId(merid, hpRealCard.getIccardid())) {
				// 写HIS系统绑定记录
				HpMerCardlist hpMerCardlist = new HpMerCardlist();
				hpMerCardlist.setMerId(merid);
				hpMerCardlist.setIccardId(hpRealCard.getIccardid());
				hpMerCardlist.setHealthCardId(vo.getHealthCardId());
				hpMerCardlist.setDate0(new Date());
				// flag0：1为第一次创建实体卡的标记，2为通过1019接口访问实体卡的标记
				hpMerCardlist.setFlag0(1);
				hpMerCardlistService.save(hpMerCardlist);
			}
		}

		QueryCardHolderVo queryCardHolderVo = new QueryCardHolderVo();
		MyBeanUtils.copyBeanNotNull2Bean(vo, queryCardHolderVo);

		// 查询健康卡资料信息
		ResResultVo resultVo = doA1019(queryCardHolderVo, merid);
		HpCardholder hpCardholder = new HpCardholder();
		MyBeanUtils.copyBeanNotNull2Bean(resultVo, hpCardholder);
		// hpApplycardService.doSendAreaPlatform(hpCardholder, merid,
		// hpRealCard);
		// 向区域平台发送数据
		hpApplycardService.doSendAreaPlatform(hpCardholder, vo.getHealthCardId(), hpRealCard);

		// --------------------银联异步--------------------
		// 把传送给网银的绑定记录写入队列表
		// HpIfaceMerchant gateway =
		// hpIfaceMerchantService.getGateway("支付网关",99);
		// if(gateway!=null){
		// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
		// hpIfaceMsgqueue.setMerId(gateway.getMerId());
		// hpIfaceMsgqueue.setFuncid("P1003");
		// hpIfaceMsgqueue.setIcCardId(hpRealCard.getIccardid());
		// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(hpRealCard.getType())));
		// hpIfaceMsgqueue.setHealthcardid(hpRealCard.getCardPkid());
		// hpIfaceMsgqueue.setDocuid(hpCardholder.getDocuId());
		// hpIfaceMsgqueue.setDocutype(hpCardholder.getDocuType());
		// hpIfaceMsgqueue.setCreatetime(new Date());
		// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
		// }
		return resultVo;
	}

	/**
	 * 实体健康卡注销/挂失/解除挂失（商户卡管）
	 */
	@Override
	public ReturnVo doA1022(RealCardCancelVo realCardCancelVo, boolean isSend) throws Exception {
		ResResultVo resultVo = null;
		String reason = "";
		if (StringUtils.isEmpty(realCardCancelVo.getHealthCardId())
				&& StringUtils.isEmpty(realCardCancelVo.getIcCardId())) {
			if (StringUtils.isEmpty(realCardCancelVo.getNationality())
					|| StringUtils.isEmpty(realCardCancelVo.getDocuId())
					|| StringUtils.isEmpty(realCardCancelVo.getDocuType())) {
				reason = "(国籍，证件类型，证件号码)或健康卡号或实体卡卡号不能同时为空";
				throw new BusException(reason);
			}
		}
		if (null == realCardCancelVo.getOptType()) {
			throw new BusException("操作类型不能为空");
		}
		// 如果为解挂，实体卡不能为空
		if (2 == realCardCancelVo.getOptType().intValue() && StringUtils.isEmpty(realCardCancelVo.getIcCardId())) {
			throw new BusException("实体卡卡号不能为空");
		}

		if (1 == realCardCancelVo.getType().intValue()) {
			throw new BusException("社保卡不允许注销/挂失/解挂操作");
		}
		Integer type = realCardCancelVo.getType();
		String healthCardId = realCardCancelVo.getHealthCardId();
		String icCardid = realCardCancelVo.getIcCardId();
		String identityId = null;
		if (StringUtils.isEmpty(healthCardId) && StringUtils.isEmpty(icCardid)) {
			QueryIdVo vo = new QueryIdVo();
			MyBeanUtils.copyBeanNotNull2Bean(realCardCancelVo, vo);
			resultVo = this.doA1002(vo);
			if (null == resultVo || "2".equals(resultVo.getStatus())) {
				throw new BusException("没有找到相应的健康卡信息");
			}
			healthCardId = resultVo.getHealthCardId();
			identityId = resultVo.getNationality() + resultVo.getDocuType() + resultVo.getDocuId();
		}
		HpRealCard hpRealCard = null;
		if (StringUtils.isNotEmpty(icCardid)) {
			if (null == type) {
				reason = "实体卡类型不能为空";
				throw new BusException(reason);
			}

			// if(realCardCancelVo.getOptType().intValue()==2){//解挂
			// hpRealCard = hpRealCardService.getHealthCardId(icCardid,
			// 2,realCardCancelVo.getType());
			// }else{
			// hpRealCard = hpRealCardService.getHealthCardIdTwo(icCardid,
			// type);
			// }
			// 获取对应健康卡数据-------注：健康卡注销即为作废，因此数据是唯一的
			if (type == 0) {
				hpRealCard = hpRealCardService.getRealCard(icCardid, type);
			}
			// 获取对应社保卡数据------注：社保卡注销后能再启用，查询时取非注销的社保卡数据
			if (type == 1) {
				hpRealCard = hpRealCardService.getHealthCardIdNotCancel(icCardid, type);
			}
			if (null == hpRealCard) {
				reason = "不存在的实体卡卡号";
				throw new BusException(reason);
			}
			// 如果解挂，需要判断该实体卡是否已经挂失
			if (2 == realCardCancelVo.getOptType().intValue() && 2 != hpRealCard.getStatus().intValue()) {
				throw new BusException("该实体卡没有挂失，不能解挂操作");
			}
			//如果挂失，需要判断该实体卡是否正常
			if (1 == realCardCancelVo.getOptType().intValue() && 1 != hpRealCard.getStatus().intValue()) {
				String statusStr = "";
				if (0 == hpRealCard.getStatus().intValue()) {
					statusStr = "已注销";
				}
				if (2 == hpRealCard.getStatus().intValue()) {
					statusStr = "已挂失";
				}
				throw new BusException("该实体卡" + statusStr);
			}
			//如果注销，需要判断是否已经注销
			if (0 == realCardCancelVo.getOptType().intValue() && 0 == hpRealCard.getStatus().intValue()) {
				throw new BusException("该实体卡已注销");
			}
			//获取健康卡记录
			HpHealthcard card = hpHealthcardService.get(hpRealCard.getCardPkid());
			healthCardId = card.getPkid();
			identityId = card.getIdentityId();
		} else {
			HpHealthcard card = hpHealthcardService.get(healthCardId);
			if (null != card) {
				// 如果是解挂，肯定会有实体卡卡号，
				// 如果是注销或挂失，先要判断该健康e卡号下是否具有有效的实体卡
				identityId = card.getIdentityId();
				HpRealCard hpRealCardTmp = hpRealCardService.getIcCardId(card.getPkid(), 1L,
						realCardCancelVo.getType());
				if (null == hpRealCardTmp) {
					throw new BusException("该健康e卡没有绑定有效的实体卡");
				}
			} else {
				reason = "健康e卡号不存在";
				throw new BusException(reason);
			}
		}
		// 判断操作类型
		Integer status = 0;
		Integer oldStatus = 0;
		switch (realCardCancelVo.getOptType()) {
		case 0:// 注销
			status = 0;//注销
			oldStatus = 1;
			break;
		case 1:// 挂失
			status = 2;//挂失
			oldStatus = 1;
			break;
		case 2:// 解挂
			status = 1;//正常
			oldStatus = 2;
			// 需要判断能否解挂
			HpRealCard hpRealCardTmp = hpRealCardService.getIcCardId(healthCardId, status, realCardCancelVo.getType());
			if (null != hpRealCardTmp) {
				throw new BusException("已经具有有效的实体卡，不能解挂");
			}
			break;
		}
		// int ret = hpRealCardService.updateRealCardStatus(healthCardId,
		// status,oldStatus, realCardCancelVo.getType());
		//更新实体卡状态
		int ret = hpRealCardService.updateRealCardStatusByPkid(hpRealCard.getPkid().toString(), status);
		Integer retStatus = 0;
		ReturnVo returnVo = new ReturnVo();
		if (ret > 0) {
			// HpIfaceMerchant gateway =
			// hpIfaceMerchantService.getGateway("支付网关",99);
			retStatus = 1;
			// --------------------银联异步--------------------
			// switch (realCardCancelVo.getOptType()) {
			// case 0://注销
			// //把传送给网银的实体卡解绑记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1004");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// case 1://挂失
			// //把传送给网银的实体卡挂失记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1005");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// case 2://解挂
			// //把传送给网银的实体卡解挂记录写入队列表
			// if(gateway!=null){
			// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
			// hpIfaceMsgqueue.setMerId(gateway.getMerId());
			// hpIfaceMsgqueue.setFuncid("P1006");
			// hpIfaceMsgqueue.setHealthcardid(healthCardId);
			// hpIfaceMsgqueue.setCreatetime(new Date());
			// hpIfaceMsgqueue.setIcCardId(realCardCancelVo.getIcCardId());
			// hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
			// hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
			// hpIfaceMsgqueue.setType(Integer.valueOf(String.valueOf(realCardCancelVo.getType())));
			// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			// }
			// break;
			// }

			// 需要向已绑定的医院发送注销/挂失/解挂指令
			List<String> list = hpMerCardlistService.getHpMerCardlisByIccardId(icCardid, healthCardId);
			String funcId = "A2002";//实体健康卡卡注销/挂失/解除挂失通知（卡管HIS）
			for (String merid : list) {
				//接口地址
				HpIfaceAddress hpIfaceAddress = hpIfaceAddressService.getIfaceAddressByMerid(merid, funcId);
				if (null == hpIfaceAddress)
					continue;
				HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
				hpIfaceMsgqueue.setMerId(merid);
				hpIfaceMsgqueue.setPosturl(hpIfaceAddress.getFuncAddress());
				hpIfaceMsgqueue.setHealthcardid(healthCardId);
				hpIfaceMsgqueue.setIcCardId(icCardid);
				hpIfaceMsgqueue.setDocuid(realCardCancelVo.getDocuId());
				hpIfaceMsgqueue.setDocutype(realCardCancelVo.getDocuType());
				hpIfaceMsgqueue.setNationality(realCardCancelVo.getNationality());
				hpIfaceMsgqueue.setCreatetime(new Date());
				hpIfaceMsgqueue.setFuncid(funcId);
				hpIfaceMsgqueue.setType(realCardCancelVo.getType());
				hpIfaceMsgqueue.setOptType(realCardCancelVo.getOptType());
				//保存消息队列
				hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
			}
		}
		returnVo.setStatus(retStatus);
		// 像区域平台发送数据
		if (isSend) {
			HpCardholder hpCardholder = hpCardholderService.get(identityId);
			// HpRealCard realCard = hpRealCardService.getIcCardId(healthCardId,
			// 1L, 0L);
			HpRealCard realCard = hpRealCardService.getIcCardId(healthCardId, 1L, realCardCancelVo.getType());
			hpApplycardService.doSendAreaPlatform(hpCardholder, healthCardId, realCard);
		}
		return returnVo;
	}

	@Override
	public MerchantListVo doA1023() throws Exception {
		HpIfaceMerchant hpIfaceMerchant = new HpIfaceMerchant();
		List<MerchantVo> merchantList = new ArrayList<>();
		MerchantListVo merchantListVo = new MerchantListVo();
		hpIfaceMerchant.setSource(1);
		// 获取类型为医院的商户
		List<HpIfaceMerchant> hpIfacemerchantList = hpIfaceMerchantService.findList(hpIfaceMerchant);
		for (HpIfaceMerchant merchant : hpIfacemerchantList) {
			MerchantVo temp = new MerchantVo();
			temp.setMerId(merchant.getMerId());
			temp.setMerName(merchant.getMerName());
			merchantList.add(temp);
		}
		merchantListVo.setMerchantVoList(merchantList);
		return merchantListVo;
	}

	/**
	 * 已绑实体健康卡列表查询接口
	 */
	public ResResultRealCardListVO doA1024(HpRealCardSearch search) throws Exception {
		if (StringUtils.isEmpty(search.getDocuId()) && StringUtils.isEmpty(search.getDocuType())
				&& StringUtils.isEmpty(search.getNationality())) {// Nationality、DocuType、docuId都不为空的时候。健康E卡号不能为空
			if (StringUtils.isEmpty(search.getHealthCardId())) {
				throw new BusException("健康E卡号不能为空");
			}
		} else if (StringUtils.isEmpty(search.getHealthCardId()) && (StringUtils.isEmpty(search.getDocuId())
				|| StringUtils.isEmpty(search.getDocuType()) || StringUtils.isEmpty(search.getNationality()))) {
			throw new BusException("国籍证件类型证件号码不能为空");
		}
		ResResultRealCardListVO resResultA1024ListVO = new ResResultRealCardListVO();
		//获取全部实体卡
		List<HpRealCard> list = hpRealCardService.findListByCondition(search);
		List<A1024VO> a1024VOList = new ArrayList<A1024VO>();
		for (HpRealCard hp : list) {
			A1024VO a1024VO = new A1024VO();
			a1024VO.setHealthCardId(hp.getCardPkid());
			a1024VO.setIcCardId(hp.getIccardid());
			a1024VO.setStatus(hp.getStatus());
			a1024VO.setType(hp.getType());
			a1024VOList.add(a1024VO);
		}
		resResultA1024ListVO.setA1024VOList(a1024VOList);
		return resResultA1024ListVO;
	}

	// 解除绑定
	@Transactional
	@Override
	public ReturnVo doA1025(RealCardVo vo, String merid) throws Exception {
		// 先查询有效的实体卡卡号
		HpRealCard realCard = hpRealCardService.getIcCardId(vo.getHealthCardId(), 1L, vo.getType());
		HpRealCard hpRealCard = new HpRealCard();
		ReturnVo returnVo = new ReturnVo();
		if (null != realCard) {
			if (realCard.getIccardid().equals(vo.getIcCardId())) {
				// 清空绑定的健康卡数据
				hpRealCard.setCardPkid(null);
				hpRealCard.setPkid(realCard.getPkid());
				hpRealCard.setIccardid(vo.getIcCardId());
				hpRealCard.setType(0L);
				hpRealCard.setStatus(0L);
				hpRealCard.setStr03(null);// str03存放商户id
				hpRealCardService.save(hpRealCard);
				returnVo.setStatus(1);
			} else {
				throw new BusException("绑定的实体卡信息有误");
			}
		} else {
			throw new BusException("该卡不存在获未绑定实体卡");
		}
		// 向区域平台发送数据
		HpCardholder hpCardholder = new HpCardholder();
		hpApplycardService.doSendAreaPlatform(hpCardholder, hpRealCard.getCardPkid(), hpRealCard);

		// --------------------银联异步--------------------
		// 把传送给网银的绑定记录写入队列表
		// HpIfaceMerchant gateway =
		// hpIfaceMerchantService.getGateway("支付网关",99);
		// if(gateway!=null){
		// HpIfaceMsgqueue hpIfaceMsgqueue = new HpIfaceMsgqueue();
		// hpIfaceMsgqueue.setMerId(null);
		// hpIfaceMsgqueue.setFuncid("P1004");
		// hpIfaceMsgqueue.setIcCardId(hpRealCard.getIccardid());
		// hpIfaceMsgqueue.setType(0);
		// hpIfaceMsgqueue.setHealthcardid(null);
		// hpIfaceMsgqueue.setDocuid(null);
		// hpIfaceMsgqueue.setDocutype(null);
		// hpIfaceMsgqueue.setCreatetime(new Date());
		// hpIfaceMsgqueueService.save(hpIfaceMsgqueue);
		// }
		return returnVo;
	}

}
