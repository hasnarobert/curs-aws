package cursaws.webexample.impl;

public class Greeting {

  private static final String GREETING_PATTERN = "Hello %s!";

  private String name;
  private String message;

  public Greeting(String name) {
    this.name = name;
    buildGreeting();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    buildGreeting();
  }

  public String getMessage() {
    return message;
  }

  private void buildGreeting() {
    message = String.format(GREETING_PATTERN, name);
  }
}
