package base.异常;

/**
 * 自定义异常
 *
 * 如何自定义异常类?
 * 1.继承于现有的异常结构: RuntimeException 、Exception
 * 2.提供全局常量: serialVersionUID
 * 3.
 *
 */
public class MyException extends RuntimeException{
    static final long serialVersionUID = -7034897193246939L;//唯一标识
    public MyException(){

    }
    public MyException(String msg){
        super(msg);
    }

} //自定义异常类   继承
