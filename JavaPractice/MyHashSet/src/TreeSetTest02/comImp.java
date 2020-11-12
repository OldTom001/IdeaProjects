package TreeSetTest02;

import java.util.Comparator;

public class comImp implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
//                s1是当前对象, s2是上一个对象
        int num = s1.getAge() - s2.getAge();
        int num2 = num==0 ? s1.getName().compareTo(s2.getName()) : num; //String类已经重写了compareTo
        return num2;
    }
}
