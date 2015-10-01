package problem2;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Employee {
    private String id;        // This is employee Id
    private String managerId; // Id of this employee's manager

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
