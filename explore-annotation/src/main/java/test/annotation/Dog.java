package test.annotation;

import java.lang.annotation.*;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */

@Documented // used to ensure that classes using this annotation show this in their generated JavaDoc
@Inherited  // marks that this annotation is inheritable to sub-classes and applicable only to classes,interfaces
@Deprecated // marks that this annotation is not recommended and may be changed in future
@Target(ElementType.TYPE) // marks that this annotation is applicable only to classes,interfaces
@Retention(RetentionPolicy.RUNTIME) // marks that this annotation is available at run time
/* @Repeatable // available in java 8, indicates that an annotation annotated with this one can be
 applied more than once to the same element declaration.*/

public  @interface Dog{
    public String type() default "black";
    public String name();
}

