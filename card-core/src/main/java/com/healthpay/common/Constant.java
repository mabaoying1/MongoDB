package com.healthpay.common;

public class Constant {

	/** 区域：国家 */
	public static final String AREA_TYPE_1 = "1";
	/** 区域：省 */
	public static final String AREA_TYPE_2 = "2";
	/** 区域：市 */
	public static final String AREA_TYPE_3 = "3";
	/** 区域：县（区） */
	public static final String AREA_TYPE_4 = "4";
	/** 区域：街道（乡镇） */
	public static final String AREA_TYPE_5 = "5";
	/** 区域：村（社区） */
	public static final String AREA_TYPE_6 = "6";

	/** 自动审核 */
	public static final String OPERATE_AUTO = "2";
	/** 审核通过 */
	public static final String OPERATE_AUDIT = "1";
	/** 审核不通过 */
	public static final String OPERATE_REFUSE = "0";

	/** 接口状态 */
	public class IfaceStatus {
		/** 未申请 */
		public static final String NONE = "0";
		/** 已销户 */
		public static final String INVALID = "5";
		/** 已申请 */
		public static final String APPLIED = "1";
		/** 待激活 */
		public static final String ACTIVATE = "2";
		/** 已开卡 */
		public static final String OPEDCARD = "3";
		/** 已拉黑 */
		public static final String BLACKED = "4";

		/** 审核中 */
		public static final String AUDITING = "1";
		/** 审核通过 */
		public static final String AUDIT = "2";
		/** 审核不通过 */
		public static final String REFUSE = "3";

		/** 成功（通用） */
		public static final String SUCCESS = "1";
		/** 失败（通用） */
		public static final String FAILURE = "0";
		/** 开卡申请返回状态 */
		public static final String WAITAUDIT = "1";

	}

}
