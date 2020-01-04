package com.li.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.li.service.IKaptchaService;
import com.li.support.dto.RestResultDto;
import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Api(value = "验证码操作")
@RestController
public class KaptchaController {
    @Resource
    private DefaultKaptcha producer;
    @Resource
    private IKaptchaService kaptchaService;

    @ApiOperation(value = "得到验证码图片")
    @RequestMapping(value = "/getVerityImg", method = RequestMethod.POST)
    public void getVerityImg(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map;
        String text = producer.createText();
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ServletOutputStream  servletOutputStream = response.getOutputStream();
        ) {
            request.getSession().setAttribute("rightCode", text);
            BufferedImage image = producer.createImage(text);
            ImageIO.write(image,"jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            map = kaptchaService.createToken(text);
            map.put("image",encoder.encode(outputStream.toByteArray()));

            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            servletOutputStream.write(outputStream.toByteArray());
        } catch (IOException e) {
            throw new ServiceException("验证码创建失败");
        }
        //return RestResultDto.newSuccess(map);
    }

    @ApiOperation(value = "验证验证码")
    @RequestMapping(value = "checkVerityCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams(@ApiImplicitParam(name = "验证码",value = "verityCode", required = true))
    public RestResultDto<Boolean> checkVerityCode(HttpServletRequest request, HttpServletResponse response) {
        String rightCode = (String) request.getSession().getAttribute("rightCode");
        String verityCode = request.getParameter("verityCode");
        if (StringUtils.isBlank(rightCode)) {
            throw new ServiceException("验证码不存在");
        }
        if (StringUtils.isBlank(verityCode)) {
            throw new ServiceException(ErrorCodeEnum.NULL);
        }
        if (!rightCode.equals(verityCode)) {
            throw new ServiceException("验证码错误");
        }
        return RestResultDto.newSuccess(true);
    }
}
