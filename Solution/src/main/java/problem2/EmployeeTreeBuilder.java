package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class EmployeeTreeBuilder {
    private List<Employee> employees = null;
    private EmployeeNode rootNode = null;

    public EmployeeNode buildTree(){
        return rootNode;
    }

    public  EmployeeTreeBuilder (List<Employee> employees){
          this.employees = employees;
          rootNode = getRoot(this.employees);
          System.out.println("Root Node ID:>>> "+rootNode.getId());
          createTree();
    }

    private EmployeeNode createTree() {
        Stack<EmployeeNode> stack = new Stack<EmployeeNode>();
        stack.push(rootNode);
        while (!stack.isEmpty()) {
            EmployeeNode node =  stack.pop();
            System.out.println("Calculating Children for node " + node.getId());
            removeNodeFromList(node);
            for (Employee employee : employees ){
                if(employee.getManagerId().equalsIgnoreCase(node.getId())){
                    EmployeeNode temp =  new EmployeeNode();
                    temp.setId(employee.getId());
                    node.getDirectReports().add(temp);
                }
            }
            stack.addAll(node.getDirectReports());
        }
        return rootNode;
    }

    private  EmployeeNode getRoot(List<Employee> employeeList){
        EmployeeNode temp = null;
        for (Employee employee : employeeList ){
            if(employee.getManagerId() == null){
                temp = new EmployeeNode();
                temp.setId(employee.getId());
                break;
            }
        }
        if (temp == null){
            throw new RuntimeException("No RootNode found");
        }
        return temp;

    }

    private void removeNodeFromList(EmployeeNode node){
        Employee empToRemove = null;
        for (Employee employee : employees){
            if (employee.getId().equalsIgnoreCase(node.getId())){
                empToRemove = employee;
                break;
            }
        }
        employees.remove(empToRemove);
    }
}
