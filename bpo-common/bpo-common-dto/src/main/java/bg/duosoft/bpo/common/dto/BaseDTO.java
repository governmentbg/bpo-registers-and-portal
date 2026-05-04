package bg.duosoft.bpo.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: ggeorgiev
 * Date: 11.01.2024
 * Time: 16:40
 */
public interface BaseDTO<T extends Serializable> extends Serializable {
    public T getId();
    public void setId(T id);
}
