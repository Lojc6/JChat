package com.lojc.jchat.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 *@Auther Lojc
 *@Date 2023/8/4
 */
public class 颜色反转 {
    public static void main(String[] args) {
        try {
            // 读取图像
            BufferedImage image = ImageIO.read(new File("C:\\Users\\17484\\Desktop\\JChat.png"));

            // 获取图像宽度和高度
            int width = image.getWidth();
            int height = image.getHeight();

            // 创建一个新的BufferedImage用于保存反转后的图像
            BufferedImage invertedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 进行颜色反转
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int invertedRgb = 0xFFFFFF - rgb;  // 颜色反转公式
                    invertedImage.setRGB(x, y, invertedRgb);
                }
            }

            // 保存反转后的图像
            ImageIO.write(invertedImage, "jpg", new File("C:\\Users\\17484\\Desktop\\JChat2.png"));

            System.out.println("颜色反转完成并保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
