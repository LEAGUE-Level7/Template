package org.jointheleague.api.template.Template.Service;


import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class LocService {
    //final String verbAPI = "https://raw.githubusercontent.com/Drulac/English-Verbs-Conjugates/master/verbs-conjugations.json";
    //private final WebClient webClient = WebClient.builder().baseUrl(verbAPI).build();
    String end = "";
    File file = new File("C:\\Users\\beckh\\IdeaProjects\\Template\\src\\main\\java\\org\\jointheleague\\api\\template\\Template\\Verbs");
    BufferedReader br;
    String answer = "";
    {
        try {
            br = new BufferedReader(new FileReader(file));
            answer = br.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //String answer = "";
    String verb = "";
    String[] words = new String[0];
    boolean debug = false;

    public String getYodaSpeak(String query) {
        end = "";
        verb = "";
        words = new String[0];
        boolean verbFound = false;
        int verbLoc = 0;
        if (query.contains(".")) {
            String[] sentence = query.split("\\.");
            for (int i = 0; i < sentence.length; i++) {
                sentence[i] = sentence[i].trim();
                if(sentence[i].charAt(0)!='I') {
                    sentence[i] = sentence[i].substring(0, 1).toLowerCase() + sentence[i].substring(1);
                }
                words = sentence[i].split(" ");
                if(debug)System.out.println(words[0]);
                for (int j = 0; j < words.length; j++) {
                    if (!verbFound&&(answer.contains('"' + words[j] + '"'))) {
                        verb = words[j];
                        if(debug)System.out.println(verb);
                        verbFound = true;
                        verbLoc=j;
                    }
                }
                if(verb.equals(words[words.length-1])) {
                    if(debug)System.out.println("Verb is last");
                    sentence[i] = sentence[i].substring(
                            sentence[i].indexOf(verb))
                            + ", " +
                            sentence[i].substring(0, sentence[i].indexOf(verb)).trim();

                } else {
                    if(debug)System.out.println("Verb isn't last");
                    if(debug)System.out.println("everything after verb: <"+sentence[i].substring(sentence[i].indexOf(verb) + verb.length() + 1)+">");
                    if(words[verbLoc+1].equals("to")){
                        sentence[i] = sentence[i].substring(
                                sentence[i].indexOf(verb) +
                                        verb.length() + 4)
                                + ", " +
                                sentence[i].substring(0, sentence[i].indexOf(verb) + verb.length()+3).trim();
                    }else {
                        sentence[i] = sentence[i].substring(
                                sentence[i].indexOf(verb) +
                                        verb.length() + 1)
                                + ", " +
                                sentence[i].substring(0, sentence[i].indexOf(verb) + verb.length()).trim();
                    }
                }
                    end += sentence[i].substring(0, 1).toUpperCase() + sentence[i].substring(1) + ". ";
                if(debug)System.out.println(end);
            }
        } else {
            String sentence = query;
                sentence = sentence.trim();
                if(sentence.charAt(0)!='I') {
                    sentence = sentence.substring(0, 1).toLowerCase() + sentence.substring(1);
                }
                words = sentence.split(" ");
                for (int j = 0; j < words.length; j++) {

                    if (!verbFound&&(answer.contains('"' + words[j] + '"'))) {
                        verb = words[j];
                        if(debug)System.out.println(verb);
                        verbFound = true;
                        verbLoc=j;
                    }
                }
            if(verb.equals(words[words.length-1])) {
                if(debug)System.out.println("Verb is last");
                sentence = sentence.substring(
                        sentence.indexOf(verb))
                        + ", " +
                        sentence.substring(0, sentence.indexOf(verb)).trim();

            } else {
                if(debug)System.out.println("Verb isn't last");
                if(debug)System.out.println("everything after verb: <"+sentence.substring(sentence.indexOf(verb) + verb.length() + 1)+">");
                if(debug)System.out.println(sentence.indexOf(verb));
                if(words[verbLoc+1].equals("to")){
                    sentence = sentence.substring(
                            sentence.indexOf(verb) +
                                    verb.length() + 4)
                            + ", " +
                            sentence.substring(0, sentence.indexOf(verb) + verb.length()+3).trim();
                }else {
                    sentence = sentence.substring(
                            sentence.indexOf(verb) +
                                    verb.length() + 1)
                            + ", " +
                            sentence.substring(0, sentence.indexOf(verb) + verb.length()).trim();
                }
            }
                        end = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
                if(debug)System.out.println(end);
        }
        return end;
    }
}



