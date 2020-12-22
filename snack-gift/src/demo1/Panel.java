package demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

/**
 * 在窗体上创建一个面板
 * 继承JPanel
 */

public class Panel extends JPanel {
    private int length;
    private boolean flag = false;  // 定义一个变量，判断游戏是否开始，默认暂停
    private boolean isDie = false;  // 判断小蛇是否活着
    private final int imageSize = 50;  // 图片的尺寸，作为面板的一个单元
    int[] windowSize = Start.getWindowSize();  // 得到画板的大小
    private int[] food = null;
    private final int timeInterval = 150;  // 每隔多长时间小蛇前进一步
    private int score;
    ArrayList<Integer[]> direction = new ArrayList<>();  // 用来存放蛇的每一节的位置坐标
    String goDirection = "R";  // 定义蛇的前进方向，默认为向右
    Timer timer;  // 创建一个定时器，作用为控制小蛇的移动

    //  初始化方法，定义小蛇的初始状态
    public void init() {
        length = 3;  // 初始长度需要大于等于3
        score = 0;
        direction.clear();
        direction.add(new Integer[]{300, 350});
        direction.add(new Integer[]{275, 350});
        direction.add(new Integer[]{250, 350});
        food = randomFood();  // 随机产生食物
    }

    //  构造器，键盘监听和定时器会一直开启（无论蛇是否死亡）
    public Panel() {
        init();
        //  将焦点放到面板上
        super.setFocusable(true);
        //  判断游戏是否开始，加一个键盘监听
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {  // 按下空格
                    if (isDie) {
                        isDie = false;
                        goDirection = "R";
                        init();  // 回到初始化状态
                    } else {
                        flag = !flag;  // 游戏的进行状态发生改变
                        repaint();  // 底层会去自动调用paintComponent
                    }
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {  // 上
                    if(!goDirection.equals("D")) {
                        goDirection = "U";
                    }
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {  // 下
                    if(!goDirection.equals("U")) {
                        goDirection = "D";
                    }
                } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {  // 左
                    if(!goDirection.equals("R")) {
                        goDirection = "L";
                    }
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {  // 右
                    if(!goDirection.equals("L")) {
                        goDirection = "R";
                    }
                }
            }
        });

        //  初始化定时器定时器，每隔多长时间执行一次指定操作，在这里是让小蛇移动一步,并且重新画图
        //  该语句只会执行一遍，所以timer只会去引用一个对象，不会变化（小蛇移动速度不可变）
        timer = new Timer(timeInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  // 小蛇移动一步,并且重新画图
                //  蛇的后一节移动到前一节
                if (flag && !isDie) {  // 游戏开始，并且蛇没有死亡
                    //  改变蛇身子坐标，先改变蛇身子位置，再改变蛇头位置
                    for (int i = direction.size() - 1; i > 0; i--) {
                        direction.set(i, direction.get(i - 1));
                    }
                    //  改变蛇头坐标,蛇头前移一个图片大小
                    if (goDirection.equals("R")) {
                        direction.set(0, new Integer[]{direction.get(0)[0] + imageSize, direction.get(0)[1]});
                        if (direction.get(0)[0] > windowSize[0] - imageSize) {  // 当超出矩形活动范围时，蛇头返回另一边
                            direction.set(0, new Integer[]{0, direction.get(0)[1]});
                        }
                    } else if (goDirection.equals("L")) {
                        direction.set(0, new Integer[]{direction.get(0)[0] - imageSize, direction.get(0)[1]});
                        if (direction.get(0)[0] < 0) {  // 当超出矩形活动范围时，蛇头返回另一边
                            direction.set(0, new Integer[]{windowSize[0] - imageSize, direction.get(0)[1]});
                        }
                    } else if (goDirection.equals("U")) {
                        direction.set(0, new Integer[]{direction.get(0)[0], direction.get(0)[1] - imageSize});
                        if (direction.get(0)[1] < 0) {  // 当超出矩形活动范围时，蛇头返回另一边
                            direction.set(0, new Integer[]{direction.get(0)[0], windowSize[1] - imageSize - 30});
                        }
                    } else if (goDirection.equals("D")) {
                        direction.set(0, new Integer[]{direction.get(0)[0], direction.get(0)[1] + imageSize});
                        if (direction.get(0)[1] > windowSize[1] - imageSize - 30) {  // 当超出矩形活动范围时，蛇头返回另一边
                            direction.set(0, new Integer[]{direction.get(0)[0], 0});
                        }
                    }
                    //  判断蛇是否死亡
                    for (int i = 1; i < length; i++) {
                        //  当蛇头位置和某节蛇身子位置重合时，蛇死亡
                        //  此处因为是Integer对象，所以用equals方法来比较大小
                        if (direction.get(i)[0].equals(direction.get(0)[0]) && direction.get(i)[1].equals(direction.get(0)[1])) {
                            isDie = true;
                            break;
                        }
                    }
                    //  判断是否吃到食物
                    if (direction.get(0)[0] == food[0] && direction.get(0)[1] == food[1]) {
                        score += 1;
                        //  随机生成食物坐标
                        food = randomFood();

                        //  增加蛇的长度
                        switch (goDirection) {
                            case "R":
                                direction.add(new Integer[]{direction.get(length - 1)[0], direction.get(length - 1)[1]});
                                break;
                            case "L":
                                direction.add(new Integer[]{direction.get(length - 1)[0] - imageSize, direction.get(length - 1)[1]});
                                break;
                            case "U":
                                direction.add(new Integer[]{direction.get(length - 1)[0], direction.get(length - 1)[1] - imageSize});
                                break;
                            case "D":
                                direction.add(new Integer[]{direction.get(length - 1)[0], direction.get(length - 1)[1] + imageSize});
                                break;
                        }
                        length++;
                    }
                    repaint();  // 改变蛇的位置坐标后重新打印画板
//                    System.out.println(timeInterval);
                }
            }
        });
        timer.start();  // 开启定时器
    }

    ;

    //  产生不在蛇身上的随机食物坐标
    public int[] randomFood() {
        int i = 0;
        int[] foodDirection = new int[2];
        while (true) {  // 不断地去产生随机食物坐标
            foodDirection[0] = imageSize * (new Random().nextInt((int) (windowSize[0] - 30) / imageSize));  // 产生[0,n)之间随机int值
            foodDirection[1] = imageSize * (new Random().nextInt((int) (windowSize[1] / imageSize)));
            while (i < length) {
                if (foodDirection[0] == (int) direction.get(i)[0] && foodDirection[1] == (int) direction.get(i)[1]) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == length) {  // 蛇的每一节身子都不在食物上
                return foodDirection;
            }
        }
    }

    //  该方法会自动执行，不需要显示的去调用,将需要画的内容写在这个方法里就会自动去执行
    //  该方法是整个程序的核心代码，主要功能为打印画板、蛇的位置、食物位置、提示信息
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        //  设置面板的背景
//        super.setBackground(new Color(77, 120, 96, 208));
//        //  在面板上画一张图片
//        Images.headerImg.paintIcon(this,g,0,0);  // this指代当前画板，g指代画笔，xy为距离画板左上角的距离
        //  改变画笔的颜色，从而更改蛇的活动范围的矩形的颜色
        g.setColor(new Color(238, 169, 169, 208));
        //  在面板上画蛇的活动范围，是一个矩形
//        int[] windowSize = Start.getWindowSize();  // 得到画板的大小
        g.fillRect(0, 0, windowSize[0], windowSize[1]);

        //  画蛇头
        if (goDirection.equals("R"))
            Images.rightImg.paintIcon(this, g, direction.get(0)[0], direction.get(0)[1]);
        if (goDirection.equals("L"))
            Images.leftImg.paintIcon(this, g, direction.get(0)[0], direction.get(0)[1]);
        if (goDirection.equals("U"))
            Images.upImg.paintIcon(this, g, direction.get(0)[0], direction.get(0)[1]);
        if (goDirection.equals("D"))
            Images.downImg.paintIcon(this, g, direction.get(0)[0], direction.get(0)[1]);

        //  画蛇身子
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, direction.get(i)[0], direction.get(i)[1]);
        }

        //  画食物
        Images.foodImg.paintIcon(this, g, food[0], food[1]);

        //  显示得分信息
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("华文行楷", Font.BOLD, 30));
        g.drawString("得分为：" + score, 0, 30);

        //  如果当前是暂停状态，在面板上打印提示信息
        if (!flag) {
            g.setColor(new Color(232, 221, 9));
            g.setFont(new Font("华文行楷", Font.BOLD, 50));
            g.drawString("点击空格继续游戏！", windowSize[0] / 4, windowSize[1] / 3);
        }
        //  在游戏开始状态下，蛇死亡后打印提示信息
        if (isDie) {
            g.setColor(new Color(232, 221, 9));
            g.setFont(new Font("华文行楷", Font.BOLD, 50));
            g.drawString("蛇死亡，游戏结束！", windowSize[0] / 4, windowSize[1] / 3);
            g.drawString("点击空格重新开始游戏！", windowSize[0] / 5, windowSize[1] / 3 + 60);
            g.setFont(new Font("华文行楷", Font.BOLD, 40));
            g.setColor(new Color(12, 1, 1));
            g.drawString("2020.12.23", windowSize[0] / 4 * 2 + 100, windowSize[1] / 4 * 2 + 100);
            g.drawString("小蛇献给宝贝!", windowSize[0] / 4 * 2 + 100, windowSize[1] / 4 * 2 + 140);
            Images.muaImg.paintIcon(this, g, windowSize[0] / 4 * 3, windowSize[1] / 4 * 2);
        }
    }
}
