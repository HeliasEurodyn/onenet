package com.ed.onenet.service;

import com.ed.onenet.dto.UserDTO;
import com.ed.onenet.rest_template.OneNetRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class IdsResourceService {

    private final UserService userService;
    private final OneNetRestTemplate oneNetRestTemplate;

    public IdsResourceService(UserService userService,
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
                                          Map<String, String> headers) {

//        UserDTO userDTO = userService.getCurrentUser(headers.get("authorization"));
//
//        Map<String, Object> jsonLdParameters = this.parametersToJsonLd(parameters, userDTO);
//        String entityId = this.parametersToJsonLdId(parameters);
//
//        Boolean exists = this.oneNetRestTemplate.checkExistance("", "", entityId);
//
//
//        if (exists) {
//             this.oneNetRestTemplate.update(jsonLdParameters, headers,"", entityId);
//        } else {
//             this.oneNetRestTemplate.post(jsonLdParameters, headers);
//        }
//
//        Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(entityId);
//
//        Map<String, Map<String, Object>> responseParameters = this.jsonLdToParameters(responce);
//
//       // this.oneNetRestTemplate.saveOnenetResponse(responseParameters, formId, headers);

        return null;
    }

    public Map<String, Map<String, Object>> getFromProvider(String id,
                                                            Map<String, String> headers) {

        Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(id,"");
        Map<String, Map<String, Object>> parameters = this.jsonLdToParameters(responce);
      //  this.oneNetRestTemplate.saveOnenetResponse(parameters, id, headers);
        return parameters;
    }

    public Map<String, Object> saveResponce(String id,
                                            Map<String, String> headers) {

        Map<String, Object> responce = this.oneNetRestTemplate.getFromProvider(id, "");

        Map<String, Map<String, Object>> parameters = this.jsonLdToParameters(responce);

        return responce;
    }

    public Map<String, Object> parametersToJsonLd(Map<String, Map<String, Object>> parameters, UserDTO userDTO) {

        Map<String, Object> response = new HashMap<>();

        parameters.forEach((componentName, fields) -> {
            response.put("id", "urn:ngsi-ld:" + componentName + ":" + fields.get("onenet_id"));
            response.put("type", componentName);
            response.put("@context", Arrays.asList("https://fiware.github.io/data-models/context.jsonld",
                    "https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context-v1.3.jsonld"));

            Map<String, Object> resourseCreatorField = new HashMap<>();
            resourseCreatorField.put("type", "Property");
            resourseCreatorField.put("value", userDTO);
            response.put("onenet_resourse_creator", resourseCreatorField);

            fields
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue() != null)
                    .filter(e -> e.getValue() != "")
                    .filter(e -> e.getKey() != "sub-entities")
                    .filter(e -> e.getKey() != "id")
                    .forEach(e -> {
                        Map<String, Object> responseField = new HashMap<>();
                        responseField.put("type", "Property");
                        responseField.put("value", e.getValue());
                        response.put(e.getKey(), responseField);
                    });
        });

        return response;
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
                            (e.getKey() == "https://uri.fiware.org/ns/data-models#language" ? "language" : e.getKey()),responseFieldValue.get("value"));
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

    public String parametersToJsonLdId(Map<String, Map<String, Object>> parameters) {

        for (Map.Entry<String, Map<String, Object>> entry : parameters.entrySet()) {
            String componentName = entry.getKey();
            Map<String, Object> fields = entry.getValue();
            return componentName + ":" + fields.get("onenet_id");
        }

        return "";
    }

}
