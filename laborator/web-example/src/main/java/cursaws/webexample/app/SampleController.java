package cursaws.webexample.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cursaws.webexample.impl.Greeting;
import cursaws.webexample.impl.SampleService;
import cursaws.webexample.impl.SampleServiceImpl;

@Controller
public class SampleController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    return "index";
  }

  private SampleService sampleService = new SampleServiceImpl();

  @RequestMapping(value = "/greet", method = RequestMethod.GET)
  @ResponseBody
  public Greeting greet(@RequestParam(value = "name", required = false) String name) {
    if (name == null || name.isEmpty()) {
      return sampleService.createGreeting("world");
    } else {
      return sampleService.createGreeting(name);
    }
  }
}
