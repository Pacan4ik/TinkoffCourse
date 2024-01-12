package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Collections;

public class Task5 {
    private Task5() {
    }

    public enum Order {
        DESK,
        ASC
    }

    public static Contact[] parseContacts(String[] names, Order order) {
        if (names == null || names.length == 0) {
            return new Contact[0];
        }
        Contact[] parsed = new Contact[names.length];
        for (int i = 0; i < names.length; i++) {
            parsed[i] = new Contact(names[i]);
        }
        if (order == Order.DESK) {
            Arrays.sort(parsed, Collections.reverseOrder());
        } else {
            Arrays.sort(parsed);
        }
        return parsed;
    }

}

