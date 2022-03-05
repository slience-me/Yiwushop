package xyz.slienceme.project_shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Image;
import xyz.slienceme.project_shop.mapper.ImageMapper;
import xyz.slienceme.project_shop.service.IImageService;
import xyz.slienceme.project_shop.utils.DateUtil;

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
    @Value("${file.fileupload}")
    private String fileupload;
    @Value("${spring.path.url}")
    private String baseUrl;

    @Override
    public Result uploadImg(MultipartFile multipartFile) throws Exception {

        ApplicationHome ah = new ApplicationHome(getClass());
        File parentPathStringLinux = ah.getSource();
        String date = DateUtil.getDate("yyyyMMdd");
        String dirPath = parentPathStringLinux.getParentFile().toString().replace("\\", "/") + fileupload + date + "/";
        File filePath = new File(dirPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = date + uuid + filename;
        try {
            //导入文件
            multipartFile.transferTo(new File(dirPath, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String path = dirPath + filename;*/
        String url = baseUrl + "/image" + fileupload + date + "/" + filename;
        Image image = new Image();
        image.setImageUrl(url);
        imageMapper.insertSelective(image);
        int i = imageMapper.selectByPath(url);
        HashMap<String, Object> data = new HashMap<>();
        data.put("url", url);
        data.put("imageId", i);
        //System.out.println("data = " + data);
        return Result.createBySuccess(data);
    }
}
