package com.werikis.uolhost.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Service
@Getter
public class CodinameService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;   

    private List<String> avangersCodinameList = new ArrayList<>();
    private List<String> justiceLeagueList = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadJsonData(){
        try {
            String codinameReponse = restTemplate.getForObject(environment.getProperty("avangers"), String.class);

            JsonNode jsonNode = objectMapper.readTree(codinameReponse);

            ArrayNode avangers = (ArrayNode) jsonNode.get("vingadores");

            for (JsonNode item : avangers) {
                this.avangersCodinameList.add(item.get("codinome").asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadXmlData(){
        try {
            
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(environment.getProperty("justice.league"));

            NodeList codenameList = document.getElementsByTagName("codinome");
            
            for(int i = 0; i <codenameList.getLength(); i++){
                Element codinameElement = (Element) codenameList.item(i);
                String codiname = codinameElement.getTextContent();
                this.justiceLeagueList.add(codiname);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
