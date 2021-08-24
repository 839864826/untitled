package 泛型;
//自定义泛型类
public class Order<T> {
    String name;
    String id;
    T t;

    public Order() {
//        T[] arr = new T[10];//编译不通过
        T[] arr =(T[]) new Object[10];//只能这种样子写
    }

    public Order(String name, String id, T t) {
        this.name = name;
        this.id = id;
        this.t = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", t=" + t +
                '}';
    }
}
