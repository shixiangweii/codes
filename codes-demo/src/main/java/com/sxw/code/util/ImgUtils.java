package com.sxw.code.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-05-08
 * Time: 16:47
 *
 * @author shixiangweii
 */
public class ImgUtils {
    private static final String PREFIX = "http";
    /**
     * 风友汇图片路径
     */
    private static final String FYH_IMG_BUCKET = "fyh_img/";
    /**
     * 优医邦图片路径
     */
    private static final String YYB_IMG_BUCKET = "doc_info_imgs/";

    private static final String IMG_URL_DOMAIN = "http://download.fengyouhui.net/";

    private static final String IMG_CUTABLE_DOMAIN = "http://img.fengyouhui.net/";

    public static String concatImgWholeUrl(String fileName, String domain, String path) {
        boolean fyh = StringUtils.isNoneBlank(fileName) && fileName.startsWith(FYH_IMG_BUCKET);
        boolean yyb = StringUtils.isNoneBlank(fileName) && fileName.startsWith(YYB_IMG_BUCKET);

        if (fyh || yyb) {
            return domain + fileName;
        }

        if (StringUtils.isNotBlank(fileName) && !fileName.startsWith(PREFIX)) {
            return domain + path + fileName;
        }

        return fileName;
    }

    public static String concatImgWholeUrlByFyh(String fileName) {

        if (StringUtils.isNoneBlank(fileName) && fileName.startsWith(FYH_IMG_BUCKET)) {
            return IMG_URL_DOMAIN + fileName;
        }

        return concatImgWholeUrl(fileName, IMG_URL_DOMAIN, FYH_IMG_BUCKET);
    }

    public static String concatImgWholeUrlByDocInfo(String fileName) {
        if (StringUtils.isNoneBlank(fileName) && fileName.startsWith(YYB_IMG_BUCKET)) {
            return IMG_URL_DOMAIN + fileName;
        }

        return concatImgWholeUrl(fileName, IMG_URL_DOMAIN, YYB_IMG_BUCKET);
    }

    /**
     * 支持图片裁剪的OSS图片域名
     */
    public static String concatImgWholeUrlByCutableDomainDocInfo(String fileName) {


        if (StringUtils.isNoneBlank(fileName) && fileName.startsWith(YYB_IMG_BUCKET)) {
            return IMG_CUTABLE_DOMAIN + fileName;
        }

        return concatImgWholeUrl(fileName, IMG_CUTABLE_DOMAIN, YYB_IMG_BUCKET);
    }
}
