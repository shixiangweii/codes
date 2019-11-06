package com.sxw.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-06-21
 * Time: 10:57
 *
 * @author shixiangweii
 */
public class InstantPropertyUtils {
    private static final Logger logger = LoggerFactory.getLogger(InstantPropertyUtils.class);

    public static String getPropertiesInstant(String key, String defaultValue) {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("constant.properties");
        try {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            String property = properties.getProperty(key);
            property = new String(property.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            return property;
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        return defaultValue;
    }
}
