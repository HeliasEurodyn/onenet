package com.ed.onenet.service;

import com.ed.onenet.dto.UserDTO;
import com.ed.onenet.rest_template.OneNetRestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class EntityService {

    private final UserService userService;
    private final OneNetRestTemplate oneNetRestTemplate;

    public EntityService(UserService userService,
                         OneNetRestTemplate oneNetRestTemplate) {
        this.oneNetRestTemplate = oneNetRestTemplate;
        this.userService = userService;
    }

    public Map<String, Object> getObject(String componentName,
                                         String id) {

        Map<String, Object> jsonLdParameters = this.sourceRegistrationParameters(componentName, id);

        return null;
    }

    public Map<String, Object> postObject(String formId,
                                          Map<String, Map<String, Object>> parameters,
                                          Map<String, String> headers) throws JsonProcessingException {

        UserDTO userDTO = userService.getCurrentUser(headers.get("authorization"));

        Map<String, Object> jsonLdParameters = this.parametersToJsonLd(parameters);

        String entityId = "dataentity:" + parameters.get("data_send").get("id");

        Boolean exists = this.oneNetRestTemplate.checkExistance(entityId);

        if (exists) {
            this.oneNetRestTemplate.update(jsonLdParameters, headers, entityId);
        } else {
            this.oneNetRestTemplate.post(jsonLdParameters, headers);
        }

 //       Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(entityId);

  //      Map<String, Map<String, Object>> responseParameters = this.jsonLdToParameters(responce);

   //     this.oneNetRestTemplate.saveOnenetResponse(responseParameters, formId, headers);

        return jsonLdParameters;
    }

    public Map<String, Map<String, Object>> getFromProvider(String id,
                                                            Map<String, String> headers) {

        Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(id);
        Map<String, Map<String, Object>> parameters = this.jsonLdToParameters(responce);
        this.oneNetRestTemplate.saveOnenetResponse(parameters, id, headers);
        return parameters;
    }

    public Map<String, Object> saveResponce(String id,
                                            Map<String, String> headers) {

        Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(id);

        Map<String, Map<String, Object>> parameters = this.jsonLdToParameters(responce);

        return responce;
    }

    public Map<String, Object> parametersToJsonLd(Map<String, Map<String, Object>> parameters) throws JsonProcessingException {

        Map<String, Object> parameterFields = parameters.get("data_send");

        Map<String, Object> jsonLdBody = new HashMap<>();
        jsonLdBody.put("id", "urn:ngsi-ld:dataentity:" + parameterFields.get("id"));
        jsonLdBody.put("type", "dataentity");
        jsonLdBody.put("@context", Arrays.asList("https://fiware.github.io/data-models/context.jsonld",
                "https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context-v1.3.jsonld"));
        //response.put("@context", "https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld");


     //   String fileData = parameterFields.get("message").toString();

        Map<String, Object> jsonLdField = new HashMap<>();
        jsonLdField.put("type", "Property");
     //   jsonLdField.put("value", fileData.split(",")[1]);
        jsonLdField.put("value", parameterFields.get("message").toString());
        //jsonLdField.put("value", "Hello");
        jsonLdBody.put("filedata", jsonLdField);
       // jsonLdBody.put("filedata", parameterFields.get("message"));


//        Map<String, Object> jsonLdField2 = new HashMap<>();
//        jsonLdField2.put("type", "Property");
//        jsonLdField2.put("value", "Hello");
//        jsonLdBody.put("hello", jsonLdField2);

        System.out.println(parameterFields.get("message").toString());
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(jsonLdBody);
        System.out.println(jsonString);

        return jsonLdBody;
    }

    public Map<String, Map<String, Object>> jsonLdToParameters(Map<String, Object> response) {
        Map<String, Map<String, Object>> parameters = new HashMap<>();
        Map<String, Object> ids_resources = new HashMap<>();

        response
                .entrySet()
                .stream()
                .filter(e -> e.getKey() != "onenet_resourse_creator")
                .filter(e -> e.getKey() != "id")
                .filter(e -> e.getKey() != "@context")
                .filter(e -> e.getKey() != "type")
                .forEach(e -> {
                    Map<String, Object> responseFieldValue = (Map<String, Object>) e.getValue();
                    ids_resources.put(
                            (e.getKey() == "https://uri.fiware.org/ns/data-models#language" ? "language" : e.getKey()), responseFieldValue.get("value"));
                });

        String urnId = (String) response.get("id");
        String[] idParts = urnId.replace("urn:ngsi-ld:", "").split(":");
        ids_resources.put("onenet_type", "received");
        // ids_resources.put("id", idParts[1]);

        Map<String, Object> resourceCreator = (Map<String, Object>) response.get("onenet_resourse_creator");
        Map<String, String> resourceCreatorFields = (Map<String, String>) resourceCreator.get("value");
        ids_resources.put("onenet_user_id", resourceCreatorFields.get("id"));
        ids_resources.put("onenet_user_username", resourceCreatorFields.get("username"));
        ids_resources.put("onenet_user_email", resourceCreatorFields.get("email"));

        parameters.put(idParts[0], ids_resources);

        return parameters;
    }

    public Map<String, Object> sourceRegistrationParameters(String componentName, String id) {

        Map<String, Object> response = new HashMap<>();
        response.put("@context", Arrays.asList("https://fiware.github.io/data-models/context.jsonld",
                "https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context-v1.3.jsonld"));
        response.put("type", "ContextSourceRegistration");

        Map<String, Object> entity = new HashMap<>();
        entity.put("type", componentName);
        entity.put("id", "urn:ngsi-ld:" + id);

        Map<String, Object> entities = new HashMap<>();
        entities.put("entities", Arrays.asList(entity));

        response.put("information", Arrays.asList(entity));

        return response;
    }

}
