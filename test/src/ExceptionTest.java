public class ExceptionTest {
    public static void main(String[] args) throws Exception {

        ExceptionTest e = new ExceptionTest();
        e.test();
    }
    void test() throws Exception {
        int a = 2/1;
        throw new Exception();
    }
}
