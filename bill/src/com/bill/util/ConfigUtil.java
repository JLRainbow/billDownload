package com.bill.util;
/*
 * 服务号相关信息配置类
 */
public class ConfigUtil {
	/**
	 * 微信(302)服务号相关信息
	 */
	public final static String WX_APP_ID_302 = "wxa3e31b0213ba34cb";// 服务号的应用号
	public final static String WX_MCH_ID_302= "1388941302";// 商户号
	public final static String WX_API_KEY_302 = "qwertyuiopasdfghjklzxcvbnm123456";// API密钥
	public final static String WX_APP_SECRECT = "";// 服务号的应用密码
	public final static String WX_TOKEN = "weixinCourse";// 服务号的配置token
	public final static String WX_SIGN_TYPE = "MD5";// 签名加密方式
	public final static String WX_CERT_PATH = "D:/apiclient_cert.p12";// 微信支付证书存放路径地址
	/**
	 * 微信(401)服务号相关信息
	 */
	public final static String WX_APP_ID_401 = "wx8d622818bea5a677";// 服务号的应用号
	public final static String WX_MCH_ID_401 = "1343997401";// 商户号
	public final static String WX_API_KEY_401 = "qwertyuiopasdfghjklzxcvbnm123456";// API密钥
	// 微信对账单接口(POST)
	public final static String WX_DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	/**
	 * 支付宝服务号相关信息
	 */
	public final static String ALIPAY_APP_ID = "2015121100957479";
	public final static String ALIPAY_APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALo7FVO0ePXDUqeB+5IuOFOr/ppoDVL6tColg7GsEyvfcm4waCLcgs8eUZWUhVKagshkp+L5OePxcn5T4pqU16Er62KHadHhqOdnC6wpDBpP9x0BrVGs2vx32u86v7Ocu2sa66G/sRTG7DkBlzFWQh54LvpjsrBpANTKt7uW+ur9AgMBAAECgYADkmiJT3fWduWZ8uNJXXuVF0Jt4bsHsWlNOaT5/mvrtoByXplQudwEbvdvoAaim/YQM+YIc3FJzvK8O42oG1y7YTNZeTDqOcuoiyzgxLB+t28Yi7c4gt8Tyd88WBBeIonf0OCTbzMo9G6l9JLkvptc7671AGadFpnkL2FCE2SunQJBAPhBjwDnz9maHQ86IYonzfDWX2yjfm0MCt6pXubmij+VcvWSDDAQl87hviLLMKQKymC/aa81wjYqcVj+6lgBCK8CQQDACjk+vZhkIq4ReFmgd6pF6Y2OkZ6vs4TaxFu47A+4yxCiuXHDPEGVWM9YAUvywRho69yY3mI9orPVoOV6spoTAkEAhignd33OPPGjso7fgSFxkSzH9JWFxLg1R46P0ofTPfktXrfGDlNpNkuXzIWxi3SpWQ8Xta9/fHYDOuzHBzfH0QJAKcePoLi62L46xI48YY7nOoKPS38e2PbjZnzlv5uL6BY1rlFOEL2hPw6tPWY60bt0xg7aVV+8W9Bz83wnsMP+hQJBAMsm23kj4MBgUySTbh0U9thjXEctXOlNDcEvzqxUpg3IXiv/8Dxbv+Y+WB2PCg/gir2V/iI9Uk26vJvh9rFk2dE=";
	public final static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	// 支付宝对账单接口(POST)
	public final static String ALIPAY_DOWNLOAD_BILL_URL = "https://openapi.alipay.com/gateway.do";
	
}
