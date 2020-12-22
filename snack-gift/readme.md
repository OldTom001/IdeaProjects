#1. 关于Java GUI
Swing是新一代图形界面工具, 导包: javax.swing.* 
主要使用了两个类, 一个是JFrame(窗口容器), 另一个是JPanel(面板容器). 其中JFrame是顶级容器, JPanel是中间容器, 本工程中的做法是将内容放在JPanel中, 再将JPanel放在JFrame中.  
swing包和awt包中均包含了事件类和监听器接口,.
使用Swing来开发图形界面比AWT更加优秀

#2. 关于Swing
顶层容器是进行图形编程的基础，一切图形化的东西都必须包括在顶层容器中。顶层容器是任何图形界面程序都要涉及的主窗口，是显示并承载组件的容器组件。在 Swing 中有三种可以使用的顶层容器，分别是 JFrame、JDialog 和 JApplet。


1. JFrame：用于框架窗口的类，此窗口带有边框、标题、关闭和最小化窗口的图标。带 GUI 的应用程序至少使用一个框架窗口。
2. JDialog：用于对话框的类。
3. JApplet：用于使用 Swing 组件的 Java Applet 类。

中间容器是容器组件的一种，也可以承载其他组件，但中间容器不能独立显示，必须依附于其他的顶层容器。常见的中间容器有 JPanel、JScrollPane、JTabbedPane 和 JToolBar。


1. JPanel：表示一个普通面板，是最灵活、最常用的中间容器。
1. JScrollPane：与 JPanel 类似，但它可在大的组件或可扩展组件周围提供滚动条。
1. JTabbedPane：表示选项卡面板，可以包含多个组件，但一次只显示一个组件，用户可在组件之间方便地切换。
1. JToolBar：表示工具栏，按行或列排列一组组件（通常是按钮）

[http://c.biancheng.net/view/1206.html](http://c.biancheng.net/view/1206.html "Swing简介")
