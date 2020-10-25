package MethodTest;

public class MTest01 {
    public static void main(String[] args) {
        isEvenNumber();
    }
    public static void isEvenNumber(){
        int number = 10;

        if(number%2 == 0){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
