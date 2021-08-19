package base.异常;

/**
 * throw:生成-一个异常对象，并抛出。
 * 使用在方法内部<->自动抛出异常对象
 */
public class ThrowTest {
    public static void main(String[] args) {
        Student student = new Student();
        try {
            student.regist(-55);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(student);
    }
}
class Student{
    private int id;
    public void regist(int id) throws Exception {
        if(id>0){
            this.id=id;
        }else{
            System.out.println("输入非法");

            //手动抛异常
            throw new Exception("您输入数据非法");
        }
    }
}