package demo1;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * 创建了窗体，并且在窗体上加入了画板
 */

public class Start {
    //  窗体的位置和大小，单位是像素
    private static int windowX;
    private static int windowY;
    private static final int windowWidth = 800;
    private static final int windowHeight = 800 + 30;  // 头部占走30像素，为了保证活动范围为800*800加上30像素

    //  创建一个静态的方法，返回窗体的大小
    public static int[] getWindowSize() {
        return new int[]{windowWidth, windowHeight};
    }

    public static void main(String[] args) {
        //  获取显示器的大小
        int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        //  将窗体设置在屏幕中央
        windowX = (screenWidth - windowWidth)/2;
        windowY = (screenHeight - windowHeight)/2;
        //  设置一个窗体
        JFrame jf = new JFrame();
        //  设置窗体标题
        jf.setTitle("贪吃蛇");
        //  设置窗体位置
        jf.setBounds(windowX, windowY, windowWidth, windowHeight);
        //  设置默认关闭操作，在关闭窗体的时候程序停止
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //  设置窗体大小不可变
        jf.setResizable(false);
        //  创建一个面板
        Panel pl = new Panel();
        //  将面板添加到窗体中
        jf.add(pl);
        //将窗体显示出来
        jf.setVisible(true);

    }
}
