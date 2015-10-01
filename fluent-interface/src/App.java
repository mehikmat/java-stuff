/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class App {
    public static void main(String[] args) {
        ProductBuilder builder = new ProductBuilder();

       builder
               .name("Jacket")
               .price(2000)
               .type("International");

        Product product = builder.build();

        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getType());
    }
} 