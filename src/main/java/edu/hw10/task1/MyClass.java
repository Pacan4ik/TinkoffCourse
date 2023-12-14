package edu.hw10.task1;

public class MyClass {
    private int intValue;

    private String stringValue;

    private boolean boolValue;

    public MyClass(@Max(1000) int intValue, @NotNull @Max(10) String stringValue, boolean boolValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.boolValue = boolValue;
    }

    public static MyClass create(@Min(-5) int intValue, String stringValue, boolean boolValue) {
        return new MyClass(intValue, stringValue, boolValue);
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public boolean isBoolValue() {
        return boolValue;
    }
}
