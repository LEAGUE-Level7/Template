package org.jointheleague.api.template.Template.Service;

import io.swagger.v3.core.util.Json;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;

@Service
public class LocService {
    final String verbAPI = "https://raw.githubusercontent.com/Drulac/English-Verbs-Conjugates/master/verbs-conjugations.json";
    private final WebClient webClient = WebClient.builder().baseUrl(verbAPI).build();
    String end = "";
    Json answer = null;
    String verb = "";
    String[] words = new String[0];

    public String getYodaSpeak(String query) {
        end = "";
        answer = null;
        verb = "";
        words = new String[0];
        if (query.contains(".")) {
            String[] sentence = query.split("\\.");
            for (int i = 0; i < sentence.length; i++) {
                sentence[i] = sentence[i].trim();
                words = sentence[i].split(" ");
                VERBS:
                for (int j = 0; j < words.length; j++) {
                    answer = webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(Json.class)
                            .block();
                    if ((answer.toString().contains('"' + words[j] + '"'))) {
                        verb = words[j];
                        System.out.println(verb);
                        break VERBS;
                    }
                }
                sentence[i] = sentence[i].substring(
                        sentence[i].indexOf(verb) +
                                verb.length())
                        + ", " +
                        sentence[i].substring(0, sentence[i].indexOf(verb) + verb.length()).toLowerCase();
                sentence[i] = (sentence[i].charAt(0) + "").toUpperCase() + sentence[i].substring(1);
                //I like to eat pizza
                //To eat pizza, I like

                end += sentence[i] + ". ";
                System.out.println(end);
            }
        } else {

            }
        return end;
        }

    }



