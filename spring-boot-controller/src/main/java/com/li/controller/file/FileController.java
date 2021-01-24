package com.li.controller.file;

import com.li.support.dto.RestResultDto;
import com.li.support.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author ljy
 * @Date 2021/1/24 20:30
 * @description
 */
@Controller
//@RequestMapping(value = "file")
public class FileController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            throw new ServiceException("文件不能为空");
        }
        attributes.addFlashAttribute("message","上传成功");
        return "redirect:home";
    }
}
