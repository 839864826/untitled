package IO流;

import java.io.Serializable;
import java.util.Objects;

/**
 * 定义一个 User 类：
 该类包含：private成员变量（int类型） id，age；（String 类型）name。

 */
public class User implements Serializable {//Serializable标识接口 Externalizable
    public static final long serialVersionUID = 56463583563546532L;
    //序列化编码  ，你自己不写   它会自动生成一个序列码，
    // 如果自己不写   而你又修改了这个类，  他在生成时会生成上次不一样的  序列码 在读取时会报错

    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, int age, String name) {

        this.id = id;
        this.age = age;
        this.name = name;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }
}
