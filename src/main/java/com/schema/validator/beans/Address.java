package com.schema.validator.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String hno;
    private String street;
    private String pinCode;
    private String state;
}

