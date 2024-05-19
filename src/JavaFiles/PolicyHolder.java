/**
 Ho Anh Khoa & Nguyen Minh Phu <Group 13>
 */
package JavaFiles;

import java.sql.Date;

public class PolicyHolder {
    private int id;
    private String name;
    private Date dob;
    private String address;

    public PolicyHolder(int id, String name, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public PolicyHolder(String name, Date dob, String address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
