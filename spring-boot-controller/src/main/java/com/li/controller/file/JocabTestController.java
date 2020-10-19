package com.li.controller.file;

import com.li.support.exception.ServiceException;
import com.li.support.util.XWPFUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author lijianyou
 * @date 2020/10/19 18:25
 * @description
 */
@Api(tags = "Jocab测试接口")
@RestController
@RequestMapping(name = "file", method = {RequestMethod.GET, RequestMethod.POST})
public class JocabTestController {

    @ApiOperation(value = "产生pdf")
    @RequestMapping(value = "generatePDF", method = {RequestMethod.GET, RequestMethod.POST})
    public void generatePDF(@RequestParam(value = "fileName", required = true) String fileName, HttpServletResponse response) {
        if (StringUtils.isBlank(fileName)) {
            throw new ServiceException("参数缺失");
        }
        String path = this.getClass().getClassLoader().getResource("file/test.docx").getPath();
        File pdfTemp = new File(fileName + ".pdf");
        XWPFUtil.wordToPdf(path, pdfTemp.getPath());
        try {
            String filename = URLEncoder.encode(fileName + ".pdf", "utf-8");
            response.reset();
            response.addHeader("Content-Disposition","attachment;filename=" + fileName);
            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = new FileInputStream(pdfTemp);
            FileCopyUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
