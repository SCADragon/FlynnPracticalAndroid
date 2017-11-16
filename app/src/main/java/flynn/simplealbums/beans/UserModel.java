package flynn.simplealbums.beans;

/**
 * Created by gavin on 2017-11-13.
 */

public class UserModel {

    private String name;
    private Integer id;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
