package demo.dublin.dashboard.api;

import demo.dublin.dashboard.models.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/properties")
public class HomeController {

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/v2")
    public Collection getLocalities() {
        return homeRepository.getAllLocalitys();
    }

    @CrossOrigin
    @GetMapping("")
    public Iterable<Home> getAll() {
        return homeRepository.findAll();
    }
}
