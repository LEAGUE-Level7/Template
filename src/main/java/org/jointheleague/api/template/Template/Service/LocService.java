package org.jointheleague.api.template.Template.Service;

import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LocService {
    final String verbAPI = "https://raw.githubusercontent.com/datmt/English-Verbs/master/verbsList";
    private final WebClient webClient = WebClient.builder().baseUrl(verbAPI).build();
    public String getYodaSpeak(String query){
        String end = "";
        if(query.contains(".")) {
            String[] sentence = query.split("\\.");
            for(int i = 0; i<sentence.length;i++){
                sentence[i]=sentence[i].trim();
                String[] words = sentence[i].split(" ");
                VERBS: for(int j = 0;j<words.length;j++){

                    String answer = webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                    if(answer.contains(words[j])){
                        break VERBS;
                    }
                }
                //I like to eat pizza
                //pizza, I like to eat

                end+=sentence[i]+". ";
            }

            return end.substring(0,end.length()-2);
        }
        else{
            return end;
        }
    }

}
