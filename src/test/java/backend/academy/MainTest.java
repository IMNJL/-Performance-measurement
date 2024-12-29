package backend.academy;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testDirectAccess() {
        Main.Student student = new Main.Student("Grigory", "Kuranov");
        assertEquals("Grigory", student.name());
    }

    @Test
    void testReflection() throws Exception {
        Main.Student student = new Main.Student("Grigory", "Kuranov");
        Method method = Main.Student.class.getMethod("name");
        String result = (String) method.invoke(student);
        assertEquals("Grigory", result);
    }

    @Test
    void testLambdaMetafactory() throws Throwable {
        Main.Student student = new Main.Student("Grigory", "Kuranov");

        // Set up LambdaMetafactory
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(Main.Student.class, "name", MethodType.methodType(String.class));
        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(java.util.function.Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                MethodType.methodType(String.class, Main.Student.class)
        );
        MethodHandle lambdaHandle = callSite.getTarget();

        // Invoke Lambda
        java.util.function.Function<Main.Student, String> function =
                (java.util.function.Function<Main.Student, String>) lambdaHandle.invoke();
        String result = function.apply(student);

        assertEquals("Grigory", result);
    }

    @Test
    void testStudentConstructor() {
        Main.Student student = new Main.Student("John", "Doe");
        assertEquals("John", student.name());
        assertEquals("Doe", student.surname());
    }

    @Test
    void testStudentConstructorWithEmptyStrings() {
        Main.Student student = new Main.Student("", "");
        assertEquals("", student.name());
        assertEquals("", student.surname());
    }

    @Test
    void testMethodHandleSetup() throws Throwable {
        MethodHandle methodHandle = MethodHandles.lookup()
                .findVirtual(Main.Student.class, "name", MethodType.methodType(String.class));
        Main.Student student = new Main.Student("Alice", "Smith");
        String result = (String) methodHandle.invoke(student);
        assertEquals("Alice", result);
    }

    @Test
    void testInvalidMethodHandle() {
        assertThrows(NoSuchMethodException.class, () -> 
            MethodHandles.lookup().findVirtual(Main.Student.class, "invalidMethod", MethodType.methodType(String.class))
        );
    }

    @Test
    void testLambdaMetafactorySetup() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(Main.Student.class, "name", MethodType.methodType(String.class));
        CallSite callSite = LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(java.util.function.Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                MethodType.methodType(String.class, Main.Student.class)
        );
        MethodHandle lambdaHandle = callSite.getTarget();

        java.util.function.Function<Main.Student, String> function =
                (java.util.function.Function<Main.Student, String>) lambdaHandle.invoke();
        Main.Student student = new Main.Student("Eve", "Adams");
        String result = function.apply(student);

        assertEquals("Eve", result);
    }

    @Test
    void testLargeNumberOfStudents() {
        int count = 1_000_000;
        Main.Student[] students = new Main.Student[count];
        for (int i = 0; i < count; i++) {
            students[i] = new Main.Student("Name" + i, "Surname" + i);
        }
        assertEquals("Name999999", students[count - 1].name());
        assertEquals("Surname999999", students[count - 1].surname());
    }

    @Test
    void testReflectionInvalidMethod() throws NoSuchMethodException {
        Main.Student student = new Main.Student("John", "Doe");
        assertThrows(NoSuchMethodException.class, () -> {
            Method invalidMethod = Main.Student.class.getMethod("invalidMethod");
            invalidMethod.invoke(student);
        });
    }

    @Test
    void testStudentEquality() {
        Main.Student student1 = new Main.Student("John", "Doe");
        Main.Student student2 = new Main.Student("John", "Doe");
        Main.Student student3 = new Main.Student("Jane", "Smith");

        assertEquals(student1, student2);
        assertNotEquals(student1, student3);
    }

    @Test
    void testReflectionSetup() throws Throwable {
        Method method = Main.Student.class.getMethod("name");
        Main.Student student = new Main.Student("Test", "User");
        String result = (String) method.invoke(student);
        assertEquals("Test", result);
    }

}