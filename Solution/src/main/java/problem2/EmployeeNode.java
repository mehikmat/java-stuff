package problem2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class EmployeeNode {
    private String id; // employee id
    //Employees who report to this employee
    private List<EmployeeNode> directReports;

    public EmployeeNode(){
        this.id = null;
        this.directReports = new ArrayList<EmployeeNode>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<EmployeeNode> getDirectReports() {
        return directReports;
    }
    public void setDirectReports(List<EmployeeNode> directReports) {
        this.directReports = directReports;
    }

}
