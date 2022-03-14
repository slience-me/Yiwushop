package xyz.slienceme.project_shop.service;


import org.springframework.web.multipart.MultipartFile;
import xyz.slienceme.project_shop.common.Result;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface IImageService {

    /**
     * 图片上传接口
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Result uploadImg(MultipartFile multipartFile) throws Exception;

    Result selectImgs(Integer goodsId);
}
