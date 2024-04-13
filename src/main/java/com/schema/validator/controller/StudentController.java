package com.schema.validator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.schema.validator.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/students", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createPassportValidation(@RequestBody String request, HttpServletRequest httpServletRequest) {
        try {
            String requestBody = request;
            if (httpServletRequest.getContentType().equals(MediaType.APPLICATION_XML_VALUE)) {
                requestBody = studentService.convertXmlToJson(requestBody);
                System.out.println("requestBody: "+requestBody);
            }
            Set<String> validationErrors = studentService.validateStudent(requestBody);
            if (!validationErrors.isEmpty()) {
                return ResponseEntity.badRequest().body("Validation errors: " + validationErrors);
            } else {
                return ResponseEntity.ok("Student created successfully");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }
}
