package pw.cdmi.error;

public enum GlobalClientError implements ErrorMessage{
	
	/** 400 Bad Request */
	InvalidParameter("sys.error.service"),
	
	/** 400 Bad Request */
	MissingMandatoryParameter("请求中丢失了必要的参数, 请检查."),
	
	/** 400 Bad Request */
	InvalidRequest("The request is invalID,  Please check paramters."),

	/** 400 Bad Request */
	IncompleteBody("请求体包含的内容不完整，服务端无法识别, 请检查."),

	/** 401 Unauthorized */
	NoSuchAccessKey("请求头中并没有包含对应的访问授权码."),

	/** 401 Unauthorized */
	ErrorSignature("错误的请求签名."),

	/** 401 Unauthorized */
	NoPermissions("请求中包含的访问授权码并不具有访问本资源接口的权限."),
	
	BadCredentials("用户凭证信息错误"),
	
	UnSupportUsage("系统不支持进行该操作，前端逻辑存在错误或为非法请求."),
	
	/** 500 Internal Server Error */
	InternalError("The Server encountered an internal error.Please try again.");
	
	private String describe;

	private GlobalClientError(String describe) {
		this.describe = describe;
	}


	@Override
	public String getMessage() {
		return this.describe;
	}
}
