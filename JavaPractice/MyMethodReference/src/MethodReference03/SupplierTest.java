package MethodReference03;

import java.util.function.Supplier;

/*返回一个int数组中的最大值, 用函数式接口Supplier实现*/
public class SupplierTest {
    public static void main(String[] args) {
        int[] arr = {19, 20, 28, 37, 46};
//        Supplier是一个函数式接口, 这里的Lambda表达式相当于重写了其中的get方法
        int maxValue = getMax(() -> {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                max = max >= i ? max : arr[i];
            }
            return max;
        });
        System.out.println(maxValue);
    }

    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
