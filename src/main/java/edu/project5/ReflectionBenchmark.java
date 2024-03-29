package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;

@State(Scope.Thread)
public class ReflectionBenchmark {

    public void run(Options options) throws RunnerException {
        var resultCollection = new Runner(options).run();
    }

    private Student student;
    private String testMethodName = "name";
    private Method method;
    private MethodHandle methodHandle;

    private Supplier<?> getter;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        //reflection
        method = Student.class.getMethod(testMethodName);
        //method handle
        methodHandle =
            MethodHandles.lookup().findVirtual(Student.class, testMethodName, MethodType.methodType(String.class));
        //meta factory
        CallSite callSite = LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            MethodHandles.lookup().findVirtual(Student.class, testMethodName, MethodType.methodType(String.class)),
            MethodType.methodType(String.class)
        );
        getter = (Supplier<?>) callSite.getTarget().invokeExact(student);

    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = (String) getter.get();
        bh.consume(name);
    }

}
