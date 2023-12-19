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
        if (!this.secondName.isBlank() && !o.secondName.isBlank()) {
            int lastNameComparison = this.secondName.compareTo(o.secondName);
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }
        } else if (!this.secondName.isBlank()) {
            return this.secondName.compareTo(o.firstName);
        } else if (!o.secondName.isBlank()) {
            return this.firstName.compareTo(o.secondName);
        }

        return this.firstName.compareTo(o.firstName);
    }
}
