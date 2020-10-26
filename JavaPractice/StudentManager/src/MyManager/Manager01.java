package MyManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager01 {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<Student>();
        System.out.println("--------欢迎来到学生管理系统--------");
        while(true) {
            System.out.println("1. 添加学生.");
            System.out.println("2. 删除学生.");
            System.out.println("3. 修改学生.");
            System.out.println("4. 查看所有学生.");
            System.out.println("5. 退出.");

            System.out.println("请输入您的选择: ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addStudent(array);
                    break;
                case "2":
                    deleteStudent(array);
                    break;
                case "3":
                    updateStudent(array);
                    break;
                case "4":
                    findAllStudent(array);
                    break;
                case "5":
                    System.out.println("谢谢使用.");
                    System.exit(0);
                default:
                    System.out.println("您输入的数字有误, 请重新输入.");
            }
        }
    }

    public static void addStudent(ArrayList<Student> array) {
//        录入信息
        String sid;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("请输入学号");
            sid = sc.nextLine();
            boolean flag = isUsed(array, sid);
            if(flag == false) {
                break;
            }
            System.out.println("您输入的学号已经被注册, 请重新输入");
        }
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入地址");
        String address = sc.nextLine();
//        创建学生对象
        Student s1 = new Student();
        s1.setSid(sid);
        s1.setName(name);
        s1.setAge(age);
        s1.setAddress(address);
//        添加到集合
        array.add(s1);
        System.out.println("添加学生成功.");
    }

    public static boolean isUsed(ArrayList<Student> array, String sid) {
        for(int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if(s.getSid().equals(sid)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteStudent(ArrayList<Student> array) {
        System.out.println("请输入要删除的学号");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();
//        默认索引为负值, 用于判断学号是否存在
        int index  = -1;
        for(int i = 0; i < array.size(); i ++) {
            Student s = array.get(i);
            if(sid.equals(s.getSid())) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            System.out.println("该信息不存在");
        } else {
            array.remove(index);
            System.out.println("删除成功.");
        }
    }

    public static void updateStudent(ArrayList<Student> array) {
        Student s1 = new Student();
        System.out.println("请输入要更新信息的学生的学号:");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();
        int index  = -1;
        for(int i = 0; i < array.size(); i ++) {
            Student s = array.get(i);
            if(sid.equals(s.getSid())) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            System.out.println("该信息不存在");
        } else {
            s1.setSid(sid);
            System.out.println("请输入更新后的学生姓名:");
            s1.setName(sc.nextLine());
            System.out.println("请输入更新后的学生年龄:");
            s1.setAge(sc.nextLine());
            System.out.println("请输入更新后的学生地址:");
            s1.setAddress(sc.nextLine());
            array.set(index, s1);
            System.out.println("更新成功.");
        }
    }

    public static void findAllStudent(ArrayList<Student> array) {
//        判断集合中是否有数据, 若无, 显示提示信息
        if(array.size() == 0) {
            System.out.println("无信息, 请先添加信息再查询");
//            程序不再继续执行
            return;
        }
//        显示表头信息
        System.out.println("学号\t\t\t姓名\t\t年龄\t\t地址");
        for(int i = 0; i<array.size(); i++) {
            Student s = array.get(i);
            String sid = s.getSid();
            String name = s.getName();
            String age = s.getAge();
            String address = s.getAddress();
            System.out.println(sid + "\t" + name + "\t" + age + "岁\t\t" + address);
        }
    }
}
