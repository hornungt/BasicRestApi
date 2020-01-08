package simplecrud;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class User {

    @NotNull
    @NotEmpty
    private String name;

    @PositiveOrZero
    private int age;

    @PositiveOrZero
    private int idNum;

    public User(){};

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.idNum = 0;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getIdNum() { return idNum; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setIdNum(int id) { this.idNum = id; }
}
