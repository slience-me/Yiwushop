package xyz.slienceme.project_shop.utils;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import xyz.slienceme.project_shop.common.BusinessException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadUtil {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger log = LoggerFactory.getLogger(UploadUtil.class);
    private final static String[] strHex = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 图片上传
     *
     * @param file 文件
     * @throws Exception
     */
    public static String uploadImgs(MultipartFile file) throws Exception {
        if (null == file) {
            throw new BusinessException("请选择照片");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取图片后缀名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        //获取服务器地址
        String path = request.getServletContext().getRealPath("");
        String[] webapps = path.split("webapps");
        path = webapps[0] + "webapps";

        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        //新建图片名称
        String newFile = createdate + StringUtil.getUUID() + "." + ext;

        File filepath = new File(path + "/zcy-images", newFile);

        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(filepath);

        String paths = PropertyUtil.getValue("application.properties", "spring.path.url");
//        String paths = request.getScheme() + "://" + request.getServerName()
//                + ":8080";
//        String paths = request.getScheme() + "://" + UserIP.getLocalIP()+":8080";
        return paths + "/zcy-images/" + newFile;
    }

    /**
     * 图片上传
     *
     * @param multipartFile 文件
     * @throws Exception
     */
    public static String uploadImage(MultipartFile multipartFile) throws Exception {

        String fileupload = "/file/";

        ApplicationHome ah = new ApplicationHome(UploadUtil.class);
        File parentPathStringLinux = ah.getSource();
        /*System.out.println("parentPathStringLinux:"+parentPathStringLinux.getParentFile().toString());*/

        //获取项目所在位置
        /*Path path = Paths.get("");
        String parentPathString = path.toAbsolutePath().toString();
        System.out.println(parentPathString);*/

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
        path = path.replace("\\", "/");
        return path + filename;
    }


    /**
     * 文件下载功能
     *
     * @param response 响应
     * @param fileName 文件名
     * @param fileId   文件id
     */
    public static void download(HttpServletResponse response, String fileName, String fileId) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取服务器地址
        String path = request.getServletContext().getRealPath("");
        String[] webapps = path.split("webapps");
        path = webapps[0] + "webapps/zcy-file/";
        log.error("路径：" + path);
        File file = new File(path + fileId);

        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
//            String fileName=file.getName();
            if (!file.exists()) {
                //如果文件不存在就结束方法
                return;
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            //为文件设置下载后的文件名
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"");
            out = response.getOutputStream();
            //读写取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error("报错了：", e);
        } finally {
            try {
                out.close();
                ips.close();
            } catch (IOException e) {
                log.error("报错：", e);
            }
        }
    }

}
