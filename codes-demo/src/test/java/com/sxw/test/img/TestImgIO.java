package com.sxw.test.img;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:
 * User: shixiangweii
 * Date: 2019-03-08
 * Time: 15:02
 *
 * @author shixiangweii
 */
public class TestImgIO {

    @Test
    public void test() {
        Image img = null;
        try {
          //  img = ImageIO.read(new File("C:\\Users\\shixiangweii\\Desktop\\69eda063-e8ea-4fb3-94f9-5ca5e5429308.png"));
            img = ImageIO.read(new File("C:\\Users\\shixiangweii\\Desktop\\q69eda063-e8ea-4fb3-94f9-5ca5e5429308.jpg"));
         //   img = ImageIO.read(new File("C:\\Users\\shixiangweii\\Desktop\\78678669eda063-e8ea-4fb3-94f9-5ca5e542930834534534mmm.png"));
          //  img = ImageIO.read(new File("http://download.fengyouhui.net/fyh_img/69eda063-e8ea-4fb3-94f9-5ca5e5429308.jpg"));
            System.out.println(img);
            // HttpURLConnection connection = (HttpURLConnection) new URL("http://download.fengyouhui.net/fyh_img/69eda063-e8ea-4fb3-94f9-5ca5e5429308.jpg").openConnection();
            HttpURLConnection connection = (HttpURLConnection) new URL("http://download.fengyouhui.net/fyh_img/fa1312f7-0f59-488d-a9c7-0c0b0fa753c5.png").openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();

             img =   ImageIO.read(inputStream);

                inputStream.close();
            }
            System.out.println(img);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            img = null;
        }

    }
}
