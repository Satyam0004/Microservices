package com.ecommerce.user.model;

import lombok.Data;

@Data
public class Addresses {

    private Long  id;
    private String street;
    private String city;
    private String country;
    private String state;
    private String zipcode;
}
