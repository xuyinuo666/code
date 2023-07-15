package exception;


import response.ResEnum;

/**
 * @author xgw
 * @date 2020/7/11
 */
public class GoovyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResEnum resEnum;

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


	public GoovyException(ResEnum resEnum) {
		super(resEnum.getMsg());
		this.resEnum = resEnum;
	}

	public GoovyException(ResEnum resEnum, Object object) {
		super(resEnum.getMsg());
		this.resEnum = resEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResEnum getResponseEnum() {
		return resEnum;
	}

}
