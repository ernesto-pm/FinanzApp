package com.example.ernesto.finanzapp.pojos;

import java.util.Date;

/**
 * Created by ernesto on 2/22/18.
 */

public class Transaction {
    String user, category, concept; // User and Category are objectIds
    Date date;
    int quantity;
    boolean is_expenditure;
}
