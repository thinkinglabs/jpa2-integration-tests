package io.thinkinglabs;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class BaseEntity {

    public BaseEntity() {
        super();
    }

    public abstract Long getId();
}
