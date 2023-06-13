package com.shivam.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticFilteringController {
    
    @GetMapping("/static-filtering")
    public SomeBean getSomeBean(){
        return new SomeBean("value1","value2","value3");
    }


    @GetMapping("/static-filtering-list")
    public List<SomeBean> getSomeBeans(){
        return  Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
    }
}
