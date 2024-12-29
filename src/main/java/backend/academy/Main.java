package backend.academy;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class Main {
    record Student(String name, String surname) {}

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private MethodHandle lambdaHandle;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Grigory", "Kuranov");

        // Reflection setup
        method = Student.class.getMethod("name");

        // MethodHandles setup
        methodHandle = MethodHandles.lookup().findVirtual(Student.class, "name", MethodType.methodType(String.class));

        // LambdaMetafactory setup
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(java.util.function.Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                MethodType.methodType(String.class, Student.class)
        );
        lambdaHandle = callSite.getTarget();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws Throwable {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) throws Throwable {
        java.util.function.Function<Student, String> function = (java.util.function.Function<Student, String>) lambdaHandle.invoke();
        String name = function.apply(student);
        bh.consume(name);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Main.class.getSimpleName())
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .forks(1)
                .warmupForks(1)
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(5))
                .measurementIterations(10)
                .measurementTime(TimeValue.seconds(5))
                .build();

        new Runner(options).run();
    }
}
