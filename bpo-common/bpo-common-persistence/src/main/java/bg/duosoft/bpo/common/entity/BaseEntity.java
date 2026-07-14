package bg.duosoft.bpo.common.entity;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 11.01.2024
 * Time: 17:29
 */
public interface BaseEntity<T extends Serializable> extends Serializable {
    T getId();
    void setId(T t);
}
