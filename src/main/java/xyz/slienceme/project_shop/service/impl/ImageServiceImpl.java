package xyz.slienceme.project_shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Image;
import xyz.slienceme.project_shop.mapper.ImageMapper;
import xyz.slienceme.project_shop.service.IImageService;
import xyz.slienceme.project_shop.utils.UploadUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Result uploadImg(MultipartFile multipartFile) throws Exception {
        String fileupload = "/file/";

        ApplicationHome ah = new ApplicationHome(UploadUtil.class);
        File parentPathStringLinux = ah.getSource();

        //创建文件
        File file = new File(parentPathStringLinux + fileupload);
        if (!file.exists()) {
            file.mkdirs();
        }

        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + filename;

        try {
            //导入文件
            multipartFile.transferTo(new File(parentPathStringLinux + fileupload, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = parentPathStringLinux + fileupload;
        path = path.replace("\\", "/") + filename;

        Image image = new Image();
        image.setImageUrl(path);
        imageMapper.insertSelective(image);
        int i = imageMapper.selectByPath(path);
        HashMap<String, Object> data = new HashMap<>();
        data.put("path",path);
        data.put("imageId",i);
        return Result.createBySuccess(data);
    }
}
