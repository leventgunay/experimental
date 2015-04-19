package org.basesource.vending.annotations;

import org.basesource.vending.model.type.UserType;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secure {

    UserType[] only() default { UserType.ADMIN };

}
