package handler;

import cn.hutool.core.util.StrUtil;

import exception.GoovyException;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import response.ResEnum;
import response.Res;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义错误处理器，除了授权只要请求服务器成功，就返回200，错误根据错误码前端进行处理
 *
 * @author xgw
 * @date 2020/7/11
 */
@RestController
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandlerConfig {

	
	@ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
	public ResponseEntity<Res<List<String>>> methodArgumentNotValidExceptionHandler(Exception e) {
		log.error("methodArgumentNotValidExceptionHandler", e);
		List<FieldError> fieldErrors = null;
		if (e instanceof MethodArgumentNotValidException) {
			fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
		}
		if (e instanceof BindException) {
			fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
		}
		if (fieldErrors == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(Res.fail(ResEnum.METHOD_ARGUMENT_NOT_VALID));
		}

		List<String> defaultMessages = new ArrayList<>(fieldErrors.size());
		for (FieldError fieldError : fieldErrors) {
			defaultMessages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(Res.fail(ResEnum.METHOD_ARGUMENT_NOT_VALID, defaultMessages));
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Res<List<FieldError>>> methodArgumentNotValidExceptionHandler(
			HttpMessageNotReadableException e) {
		log.error("methodArgumentNotValidExceptionHandler", e);
		return ResponseEntity.status(HttpStatus.OK)
				.body(Res.fail(ResEnum.HTTP_MESSAGE_NOT_READABLE));
	}

	@ExceptionHandler(GoovyException.class)
	public ResponseEntity<Res<Object>> GoovyExceptionHandler(GoovyException e) {
		log.error("GoovyExceptionHandler", e);

		ResEnum resEnum = e.getResponseEnum();
		// 失败返回失败消息 + 状态码
		if (resEnum != null) {
			return ResponseEntity.status(HttpStatus.OK).body(Res.fail(resEnum, e.getObject()));
		}
		// 失败返回消息 状态码固定为直接显示消息的状态码
		return ResponseEntity.status(HttpStatus.OK).body(Res.showFailMsg(e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Res<Object>> exceptionHandler(Exception e) throws TransactionException {
		log.error("exceptionHandler", e);
		log.info("RootContext.getXID(): " + RootContext.getXID());
		if (StrUtil.isNotBlank(RootContext.getXID())) {
			GlobalTransactionContext.reload(RootContext.getXID()).rollback();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Res.fail(ResEnum.EXCEPTION));
	}
}
