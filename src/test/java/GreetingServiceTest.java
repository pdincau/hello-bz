import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreetingServiceTest {

    @Test
    public void it_should_greet_strangers() {
        GreetingService service = new GreetingService();

        String message = service.greet("");

        assertEquals("Hello Stranger!", message);
    }

    @Test
    public void it_should_greet_people() {
        GreetingService service = new GreetingService();

        String message = service.greet("Ivo");

        assertEquals("Hello Ivo!", message);
    }

}
