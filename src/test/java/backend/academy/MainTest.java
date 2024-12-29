package backend.academy;

import org.junit.jupiter.api.Test;

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
}