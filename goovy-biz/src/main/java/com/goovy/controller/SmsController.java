package com.goovy.controller;

import com.goovy.enums.VerificationEnums;
import com.goovy.response.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/common/sms")
@Api(tags = "短信验证码接口")
public class SmsController {
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "path", dataType = "String", name = "mobile", value = "手机号"),
//            @ApiImplicitParam(paramType = "header", dataType = "String", name = "uuid", value = "uuid"),
//    })
//    @GetMapping("/{verificationEnums}/{mobile}")
//    @ApiOperation(value = "发送短信验证码,一分钟同一个ip请求1次")
//    public Res getSmsCode(
//            @RequestHeader String uuid,
//            @PathVariable String mobile,
//            @PathVariable VerificationEnums verificationEnums) {
//        verificationService.check(uuid, verificationEnums);
//        smsUtil.sendSmsCode(mobile, verificationEnums, uuid);
//        return ResultUtil.success(ResultCode.VERIFICATION_SEND_SUCCESS);
//    }
}
