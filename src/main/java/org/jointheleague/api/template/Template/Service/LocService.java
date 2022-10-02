package org.jointheleague.api.template.Template.Service;

import org.springframework.stereotype.Service;


@Service
public class LocService {

    public String getResults(String query){
        return "Searching for books related to " + query;
    }

}
