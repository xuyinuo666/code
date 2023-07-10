package exception;


import response.ResponseEnum;

/**
 * @author xgw
 * @date 2020/7/11
 */
public class GoovyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResponseEnum responseEnum;

	public GoovyException(String msg) {
		super(msg);
	}

	public GoovyException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public GoovyException(String msg, Throwable cause) {
		super(msg, cause);
	}


	public GoovyException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
	}

	public GoovyException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

}
