package cn.com.jdbc.entity;

/**
 * Created by GL-shala on 2018/6/2.
 */
public class Person {

    private String personId;
    private String personName;
    private String compcompId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCompcompId() {
        return compcompId;
    }

    public void setCompcompId(String compcompId) {
        this.compcompId = compcompId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", compcompId='" + compcompId + '\'' +
                '}';
    }
}
