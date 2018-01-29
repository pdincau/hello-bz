class GreetingService {

    public String greet(String name) {
        if ("".equals(name)) {
            return "Hello Stranger!";
        }
        return "Hello " + name + "!";
    }

}