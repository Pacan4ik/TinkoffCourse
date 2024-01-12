package edu.hw2;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return new CallingInfo(elements[1].getClassName(), elements[1].getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }
}
