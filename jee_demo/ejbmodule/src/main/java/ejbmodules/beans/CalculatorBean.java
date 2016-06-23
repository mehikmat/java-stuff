package ejbmodules.beans;

import javax.ejb.Stateless;

/**
 * Created by hdhamee on 6/23/16.
 */

@Stateless
public class CalculatorBean {

    public String sayHello(){
        return "Hello Calculator Bean";
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int remainder(int a, int b) {
        return a % b;
    }
}
