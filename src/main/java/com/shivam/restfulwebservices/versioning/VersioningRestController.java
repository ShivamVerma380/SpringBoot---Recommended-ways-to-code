package com.shivam.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningRestController {
    
    /**
     * URI Versioning
     * @return
     */
    @GetMapping(path = "/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Shivam Verma");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Shivam","Verma"));
    }

    /**
     * Request Parameter Versioning
     * @return
     */

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Shivam Verma");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Shivam","Verma"));
    }

    /**
     * Header Versioning
     * @return
     */

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersionRequestHeader(){
        return new PersonV1("Shivam Verma");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersionRequestHeader(){
        return new PersonV2(new Name("Shivam","Verma"));
    }

    /**
     * Mime Type Versioning
     * @return
     */

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersionRequestAcceptHeader(){
        return new PersonV1("Shivam Verma");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersionRequestAcceptHeader(){
        return new PersonV2(new Name("Shivam","Verma"));
    }
    
}
