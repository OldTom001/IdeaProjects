package P3;

import P1.Student;
/*Java模块化实验, 在模块My1添加包导出描述, 在My2中添加模块依赖描述
* 即可在模My2中调用My1中定义的方法*/
public class ModuleDemo {
    public static void main(String[] args) {
        Student s = new Student();
        s.study();
//        因为Teacher所在My1\\P2包没有导出, 所以不能用
       /* Teacher t = new Teacher();
        t.teach();*/
    }
}
