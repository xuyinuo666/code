package response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一的返回数据
 *
 * @author xgw
 * @date 2020/7/3
 */
public class Res<T> implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(Res.class);

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return Objects.equals(ResEnum.OK.value(), this.code);
	}

	@Override
	public String toString() {
		return "ServerResponseEntity{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}

	public static <T> Res<T> success(T data) {
		Res<T> res = new Res<>();
		res.setData(data);
		res.setCode(ResEnum.OK.value());
		return res;
	}

	public static <T> Res<T> success() {
		Res<T> res = new Res<>();
		res.setCode(ResEnum.OK.value());
		res.setMsg(ResEnum.OK.getMsg());
		return res;
	}

	/**
	 * 前端显示失败消息
	 * @param msg 失败消息
	 * @return
	 */
	public static <T> Res<T> showFailMsg(String msg) {
		log.error(msg);
		Res<T> res = new Res<>();
		res.setMsg(msg);
		res.setCode(ResEnum.SHOW_FAIL.value());
		return res;
	}

	public static <T> Res<T> fail(ResEnum resEnum) {
		log.error(resEnum.toString());
		Res<T> res = new Res<>();
		res.setMsg(resEnum.getMsg());
		res.setCode(resEnum.value());
		return res;
	}

	public static <T> Res<T> fail(ResEnum resEnum, T data) {
		log.error(resEnum.toString());
		Res<T> res = new Res<>();
		res.setMsg(resEnum.getMsg());
		res.setCode(resEnum.value());
		res.setData(data);
		return res;
	}

	public static <T> Res<T> transform(Res<?> oldRes) {
		Res<T> res = new Res<>();
		res.setMsg(oldRes.getMsg());
		res.setCode(oldRes.getCode());
		log.error(res.toString());
		return res;
	}

}
