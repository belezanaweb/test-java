package br.com.blz.testjava.dto.support;

import java.io.Serializable;

public interface PersistableDto<I> extends Serializable {

    I getId();

    void setId(I id);

}
