package ExceptionTest;
/*自定义一个分数异常, 采用无参构造方法时, 只报异常,
采用带参构造方法时, 返回message*/
public class ScoreException extends Exception{
    public ScoreException(){}
    public ScoreException(String message) {
        super(message);
    }
}
