package backend.academy;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@SuppressWarnings("UUF_UNUSED_FIELD")
public class Main {
    private static final String NAME = "name";
    private static final int WARMUP_ITERATIONS = 5;
    private static final int MINUTES = 2;
    private static final int MEASUREMENT_ITERATIONS = 10;

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private MethodHandle lambdaHandle;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Grigory", "Kuranov");

        // Reflection setup
        method = Student.class.getMethod(NAME);

        // MethodHandles setup
        methodHandle = MethodHandles.lookup()
        .findVirtual(Student.class, NAME, MethodType.methodType(String.class));

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
    public void lambdaMetafactory(Blackhole bh) throws Throwable {
        java.util.function.Function<Student, String> function =
        (java.util.function.Function<Student, String>) lambdaHandle.invoke();
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
                .warmupIterations(WARMUP_ITERATIONS)
                .warmupTime(TimeValue.seconds(WARMUP_ITERATIONS))
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .measurementTime(TimeValue.minutes(MINUTES))
                .build();

        new Runner(options).run();
    }

    // Перемещаем запись Student в конец класса
    record Student(String name, String surname) {}
}
