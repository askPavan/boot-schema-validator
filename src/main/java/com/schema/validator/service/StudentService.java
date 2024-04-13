package com.schema.validator.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

@Service
public class StudentService {

	@Autowired
	private ObjectMapper objectMapper;

	public Set<String> validateStudent(String requestStr) throws IOException {
		Set<String> validationErrors = new HashSet<>();

		InputStream schemaAsStream = getClass().getClassLoader().getResourceAsStream("student-schema.json");
		JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);
		JsonNode jsonNode = objectMapper.readTree(requestStr);
		Set<ValidationMessage> errors = schema.validate(jsonNode);

		for (ValidationMessage error : errors) {
			validationErrors.add(error.toString());
		}

		return validationErrors;
	}

	public String convertXmlToJson(String xml) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		JsonNode jsonNode = xmlMapper.readTree(xml);
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(jsonNode);
	}

}
