package org.skypro.skyshop.controller;

import org.skypro.skyshop.error.ShopError;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ShopControllerAdvice {
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        ShopError shopError=new ShopError(
                "Product_001",
                e.getMessage()
        );
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
