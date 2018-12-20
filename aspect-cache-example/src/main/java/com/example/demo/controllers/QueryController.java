package com.example.demo.controllers;

import com.example.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "example")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @RequestMapping(method = RequestMethod.GET, value = "/{key:.+}")
    public String query(@PathVariable("key") String key) {
        return queryService.getName(key);
    }
}
