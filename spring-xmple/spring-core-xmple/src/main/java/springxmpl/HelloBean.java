package springxmpl;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class HelloBean {
    private String message;

    public void setMessage(String message){
        this.message  = message;
    }

    public void getMessage(){
        System.out.println("Your Message : " + message);
    }
}