package com.lojc.jchat.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 *@Auther Lojc
 *@Date 2023/8/4
 */
public class 白色换灰 {
    public static void main(String[] args) {
        try {
            // 读取图像
            BufferedImage image = ImageIO.read(new File("C:\\Users\\17484\\Desktop\\JChat3.png"));

            // 获取图像宽度和高度
            int width = image.getWidth();
            int height = image.getHeight();

            // 设置你想要的颜色（这里以红色为例，你可以使用任何你喜欢的颜色）
            int targetColorRGB = 0xFFFFF9F9; // 红色的RGB值为0xFFFF0000

            // 遍历图像的每个像素
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);

                    // 如果像素是白色（RGB值为0xFFFFFF），则设置为目标颜色
                    if (rgb == 0xFFFFFFFF) {
                        image.setRGB(x, y, targetColorRGB);
                    }
                }
            }

            // 保存修改后的图像
            ImageIO.write(image, "jpg", new File("C:\\Users\\17484\\Desktop\\JChat4.png"));

            System.out.println("白色替换完成并保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
