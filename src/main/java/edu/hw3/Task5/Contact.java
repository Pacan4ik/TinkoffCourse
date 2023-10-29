package edu.hw3.Task5;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private String fullName;
    private String firstName;
    private String secondName;

    Contact(@NotBlank String fullName) {
        if (fullName.isBlank()) {
            throw new RuntimeException("fullName was blank");
        }
        this.fullName = fullName;
        String[] splitted = fullName.split(" ");
        firstName = splitted[0];
        if (splitted.length > 1) {
            secondName = splitted[1];
        } else {
            secondName = "";
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        return (secondName + firstName)
            .compareTo(o.secondName + o.firstName);
    }
}
