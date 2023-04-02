public class Person {
    private String gender;
    private String name;
    public Person(){}
    public Person(String _gender, String _name){
        gender = _gender;
        name = _name;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
