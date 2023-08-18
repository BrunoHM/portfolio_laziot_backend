package com.senai.laziot.validators;

import com.senai.laziot.exception.PageableException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableValidator extends RuntimeException{

    public void validate(Pageable pageable) throws PageableException {
        if(pageable.getPageSize() > 20){
            throw new PageableException(true);
        }
    }
}
