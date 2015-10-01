/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class ProductBuilder {
    private Product product;

    public ProductBuilder(){
        product = new Product();
    }

    public ProductBuilder name(String name){
        product.setName(name);
        return this;
    }

    public ProductBuilder price(Integer age){
        product.setPrice(age);
        return this;
    }

    public ProductBuilder type(String email){
        product.setType(email);
        return this;
    }

    public Product build(){
        return product;
    }
}