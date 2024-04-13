//package com.schema.validator.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.networknt.schema.JsonSchema;
//import com.networknt.schema.JsonSchemaFactory;
//import com.networknt.schema.ValidationMessage;
////import com.github.fge.jsonschema.main.JsonSchemaFactory;
//import com.schema.validator.beans.Student;
//
//import jakarta.ws.rs.ProcessingException;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//public class StudentController1 {
//
//
//	private static final String SCHEMA_FILE_NAME = "student-schema.json";
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    @PostMapping("/withvalidation")
//    public ResponseEntity<String> createPassportValidation(@RequestBody String requestStr)
//            throws IOException, ProcessingException {
//        JsonSchema schema = loadSchemaFromClasspath(SCHEMA_FILE_NAME);
//        JsonNode jsonNode = parseJson(requestStr);
//
//        Set<ValidationMessage> errors = validateAgainstSchema(schema, jsonNode);
//
//        if (!errors.isEmpty()) {
//            String errorsCombined = combineErrors(errors);
//            return ResponseEntity.badRequest().body("Error: Please resolve the following validation errors:\n" + errorsCombined);
//
//        } else {
//            Student request = parseStudent(requestStr);
//            return ResponseEntity.ok(request.toString());
//        }
//    }
//
//    private JsonSchema loadSchemaFromClasspath(String schemaFileName) throws IOException, ProcessingException {
//        Resource resource = resourceLoader.getResource("classpath:" + schemaFileName);
//        try (InputStream inputStream = resource.getInputStream()) {
//            return JsonSchemaFactory.getInstance().getSchema(inputStream);
//        }
//    }
//
//    private JsonNode parseJson(String json) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readTree(json);
//    }
//
//    private Set<ValidationMessage> validateAgainstSchema(JsonSchema schema, JsonNode jsonNode) throws ProcessingException {
//        return schema.validate(jsonNode);
//    }
//
//    private String combineErrors(Set<ValidationMessage> errors) {
//        StringBuilder errorsCombined = new StringBuilder();
//        for (ValidationMessage error : errors) {
//            errorsCombined.append("Validation Error: ").append(error).append("\n");
//        }
//        return errorsCombined.toString();
//    }
//
//    private Student parseStudent(String json) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(json, Student.class);
//    }
//}
