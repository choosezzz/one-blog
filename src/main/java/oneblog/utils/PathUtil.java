package oneblog.utils;

import java.util.Properties;

/**
 * @author dingshuangen
 * @date 2020/9/25 10:31
 */
public class PathUtil {

    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String CATALINA_HOME = System.getProperty("catalina.home");
    public static final Properties prop = System.getProperties();
    public static final String OS_NAME = prop.getProperty("os.name").toLowerCase();
    private static final String WINDOWS = "windows";

    public static String getResourcesPath() {

        String resourcesPath;
        if (OS_NAME.contains(WINDOWS)) {
            resourcesPath = "\\src\\main\\resources\\";
        } else {
            resourcesPath = "/src/main/resources/";
        }
        return USER_DIR + resourcesPath;
    }

    public static String getConfigFilePath(String fileName) {
        String configPath;
        if (OS_NAME.contains(WINDOWS)) {
            configPath = "\\src\\main\\resources\\" + fileName;
        } else {
            configPath = "/src/main/resources/" + fileName;
        }
        return USER_DIR + configPath;
    }

    public static String getCatalinaHome() {
        return CATALINA_HOME;
    }

    public static String getWebRootPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        if (OS_NAME.contains(WINDOWS)) {
            path = path.replaceFirst("/", "").replaceFirst("\\\\","").replace("/","\\");
        }
        return path;
    }
}
