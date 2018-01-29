import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;

import java.io.IOException;

import static com.spotify.apollo.Status.BAD_REQUEST;
import static com.spotify.apollo.Status.OK;

public class Application {

    private static final GreetingService service = new GreetingService();

    public static void main(String... args) throws LoadingException {
        HttpService.boot(Application::init, "hello-bz", args);
    }

    static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("GET", "/hello", Application::greet))
                .registerAutoRoute(Route.sync("GET", "/ping", rc -> "pong"));
    }

    private static Response greet(RequestContext context) {
        try {
            String name = nameToGreetFrom(context);
            String message = service.greet(name);
            return Response.of(OK, message);
        } catch (IOException e) {
            return Response.forStatus(BAD_REQUEST);
        }
    }

    private static String nameToGreetFrom(RequestContext context) throws IOException {
        return context.request().parameter("name").orElse("");
    }
}
