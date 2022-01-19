package io.github.alphahinex.bootclasspath.controller;

import io.github.alphahinex.bootclasspath.dao.CountryDAO;
import io.github.alphahinex.bootclasspath.dao.UserDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    private final UserDAO userDAO;
    private final CountryDAO countryDAO;

    public DemoController(UserDAO userDAO, CountryDAO countryDAO) {
        this.userDAO = userDAO;
        this.countryDAO = countryDAO;
    }

    @GetMapping
    public String count() {
        return "User count: " + userDAO.customCount() + "\r\nCountry count: " + countryDAO.cc().size();
    }

}
