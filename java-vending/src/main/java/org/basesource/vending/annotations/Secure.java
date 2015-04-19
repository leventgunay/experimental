package org.basesource.vending.annotations;

import org.basesource.vending.model.UserType;

public @interface Secure {

    UserType[] only() default { UserType.ADMIN };

}
