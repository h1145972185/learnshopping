package com.neuedu.controller.manage;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/manage/product/")
public class UploadController {

    @Autowired
    IProductServic productService;

    @RequestMapping(value = "upload")
    public String upload(){
        return "upload";
    }


    /**
     * 上传图片
     * */
    @RequestMapping(value = "upload.do")
    @ResponseBody
    public ServerResponse uploadpic(@RequestParam(value = "upload_file",required = false) MultipartFile file){

        String path = Const.UPLOAD_PATH;
        return productService.uploadPic(file,path);
    }
}
