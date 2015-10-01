package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class EmployTreeClient {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();

        Employee e1= new Employee();
        e1.setId("1");
        employees.add(e1);
        System.out.println("Adding empId>> "+ e1.getId());

        Employee e2= new Employee();
        e2.setId("2");
        e2.setManagerId("1");
        employees.add(e2);
        System.out.println("Adding empId>> "+ e2.getId());

        Employee e3= new Employee();
        e3.setId("3");
        e3.setManagerId("1");
        employees.add(e3);
        System.out.println("Adding empId>> "+ e3.getId());

        Employee e4= new Employee();
        e4.setId("4");
        e4.setManagerId("1");
        employees.add(e4);
        System.out.println("Adding empId>> "+ e4.getId());

        Employee e5= new Employee();
        e5.setId("5");
        e5.setManagerId("3");
        employees.add(e5);
        System.out.println("Adding empId>> "+ e5.getId());

        Employee e6= new Employee();
        e6.setId("6");
        e6.setManagerId("3");
        employees.add(e6);
        System.out.println("Adding empId>> "+ e6.getId());


        EmployeeTreeBuilder builder = new EmployeeTreeBuilder(employees);
        EmployeeNode rootNode = builder.buildTree();

        System.out.println("<<<<<<<< ROOT NODE CHILDREN >>>>>>>>>");
        for (EmployeeNode node : rootNode.getDirectReports()){
            System.out.println(">> "+node.getId());
        }
    }
}
