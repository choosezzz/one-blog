package oneblog.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author dingshuangen
 * @Date 2020/9/29 11:47
 * 文件管理工具
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final String CONFIG_FILE = PathUtil.getResourcesPath() + "file_path.properties";

    private static final String PIC_PATH = "pic-path";
    private static final String PIC_SUFFIX = "img,png,jpeg,jpg";

    private static final Properties PROPERTY = new Properties();

    static {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(CONFIG_FILE);
            PROPERTY.load(inputStream);
            logger.error("---------------- init CONFIG_FILE finished -------------- ");
        } catch (Exception e) {
            logger.error("init CONFIG_FILE error", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public static File getPicFolder() {
        String path = PathUtil.getWebRootPath() + PROPERTY.getProperty(PIC_PATH);

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {

        if (StringUtils.isNotEmpty(fileName)) {
            String[] arr = fileName.split("\\.");
            if (arr.length > 1) {
                return arr[arr.length - 1];
            }
        }
        return null;
    }

    public static String writeFile(MultipartFile file) {
        String fileSuffix = getFileSuffix(file.getOriginalFilename());
        String newFile;
        //处理图片
        if (PIC_SUFFIX.contains(fileSuffix)) {
            newFile = "ob_pic_" + System.currentTimeMillis() + "." + fileSuffix;
            File picFolder = getPicFolder();
            File picFile = new File(picFolder, newFile);
            try {
                file.transferTo(picFile);
                return picFile.getPath().replace(PathUtil.getWebRootPath(), "")
                        .replaceFirst("static", "").replaceAll("\\\\", "/");
            } catch (IOException e) {
                logger.error("");
            }

        }
        return null;
    }
}
