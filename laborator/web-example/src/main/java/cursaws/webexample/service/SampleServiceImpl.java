package cursaws.webexample.service;

import org.springframework.stereotype.Component;

@Component("sampleService")
public class SampleServiceImpl implements SampleService {

  public Greeting createGreeting(String name) {
    return new Greeting(name);
  }
}
