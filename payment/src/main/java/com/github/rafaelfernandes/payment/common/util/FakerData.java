package com.github.rafaelfernandes.payment.common.util;


import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerData {

    private static Faker faker = new Faker(new Locale("pt", "BR"));;

    public static String product(){
        return faker.commerce().productName();
    }

}
