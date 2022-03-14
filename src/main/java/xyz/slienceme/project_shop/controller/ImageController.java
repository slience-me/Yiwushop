package xyz.slienceme.project_shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Slf4j
@Api(tags = "上传文件")
@RestController
@RequestMapping("/admin")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public Result uploadImg(@RequestParam("multipartFile") MultipartFile multipartFile
    ) throws Exception {
        log.info("上传接口调用-----------------------post----------------------</admin/upload>:");
        return imageService.uploadImg(multipartFile);
    }

    @ApiOperation("查询图片")
    @GetMapping("/image")
    public Result selectImgs(@RequestParam("goodsId") Integer goodsId) throws Exception {
        return imageService.selectImgs(goodsId);
    }

}
