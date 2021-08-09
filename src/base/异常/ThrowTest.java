package base.异常;

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