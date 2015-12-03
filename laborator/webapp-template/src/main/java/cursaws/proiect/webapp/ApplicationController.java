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

/**
 * Are rolul de a capta requesturile HTTP si de a defini metodele care sunt executate.
 */
@Controller
public class ApplicationController {

    /**
     * Componenta gestionata de Spring. Este creata ca @Bean in {@link Application}.
     */
    @Autowired
    private PersonRepository personRepository;

    /**
     * Este apelata cand se face un request la adresa "/greeting".
     *
     * Faptul ca ii lipseste {@link ResponseBody} inseamna ca metoda intoarcele numele unui template. Un template este construit dintr-un fisier HTML
     * cu variabile in el. Acele variabile sunt cautate in model si inlocuite in fisier.
     *
     * @return Numele template-ului care trebuie folosit (il gasiti in src/main/resources/greeting.html).
     */
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /**
     * Extrage din baza de date informatiile despre o anume persoana.
     *
     * Este apelata cand se face un request la o adresa care are ca expresie "/person/*"
     *
     * Faptul ca are {@link ResponseBody} inseamna ca metoda intoarce direct rezultatul si nu trebuie folosit nici un template.
     *
     * Parametrul personName este anotat cu {link @PathVariable}. Asta inseamna ca valoarea din URL cu numele personName este copiata in parametrul
     * personName.
     */
    @RequestMapping("/person/{personName}")
    @ResponseBody
    public Person person(@PathVariable("personName") String personName) {
        return personRepository.findPerson(personName);
    }

    /**
     * Salveaza in baza de date informatiile despre o anume persoana.
     *
     * Faptul ca are {@link ResponseBody} inseamna ca metoda intoarce direct rezultatul si nu trebuie folosit nici un template.
     *
     * In {@link RequestMapping} este specificat faptul ca metoda HTTP este POST. Asta inseamna ca requestul trebuie sa fie de tip POST ca aceasta
     * metoda sa fie apelata (curl -X POST http://localhost:8080/person/alex2/age/9997).
     */
    @RequestMapping(value = "/person/{personName}/age/{age}", method = RequestMethod.POST)
    @ResponseBody
    public void savePerson(@PathVariable("personName") String personName, @PathVariable("age") String age) {
        personRepository.savePerson(new Person(personName, Integer.parseInt(age)));
    }

}
