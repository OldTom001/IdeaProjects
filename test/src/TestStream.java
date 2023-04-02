import java.util.*;
import java.util.stream.Collectors;

public class TestStream {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("男","张三丰"));
        list.add(new Person("女","白骨精"));
        list.add(new Person("男","孙悟空"));
        list.add(new Person("女","刘亦菲"));
        list.add(new Person("男","老拜登"));
        List<Person> male = list.stream().filter(person -> person.getGender().equals("男")).collect(Collectors.toList());
        List<Person> female = list.stream().filter(person -> person.getGender().equals("女")).collect(Collectors.toList());
        Map<String, List<Person>> map = new HashMap<>();
        map.put("男", male);
        map.put("女", female);
        for (String s : map.keySet()) {
            System.out.print(s+":");
            System.out.println(map.get(s).toString());
        }
    }
}
