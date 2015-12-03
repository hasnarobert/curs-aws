package cursaws.proiect.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cursaws.proiect.model.Person;
import cursaws.proiect.service.PersonRepository;

@Controller
public class ApplicationController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/person/{personName}")
    @ResponseBody
    public Person person(@PathVariable("personName") String personName) {
        return personRepository.findPerson(personName);
    }

    @RequestMapping(value = "/person/{personName}/age/{age}", method = RequestMethod.POST)
    @ResponseBody
    public void savePerson(@PathVariable("personName") String personName, @PathVariable("age") String age) {
        personRepository.savePerson(new Person(personName, Integer.parseInt(age)));
    }

}
