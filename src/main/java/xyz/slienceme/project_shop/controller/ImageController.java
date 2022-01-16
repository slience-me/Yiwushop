package xyz.slienceme.project_shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.service.IImageService;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Api(tags = "上传文件")
@RestController
@RequestMapping("/upload")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private IImageService imageService;

    @ApiOperation("上传图片")
    @PostMapping("/img")
    public Result uploadImg(@RequestParam("multipartFile") MultipartFile multipartFile
    ) throws Exception {
        log.info("上传接口调用-----------------------post----------------------</upload/img>:");
        return imageService.uploadImg(multipartFile);
    }

}
