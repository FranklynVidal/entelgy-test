package com.entelgy.comments.client.api;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter
	private Problem problem;

	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);

		deserializeProblem(cause);
	}

	private void deserializeProblem(RestClientResponseException cause) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new JavaTimeModule());
		mapper.findAndRegisterModules();

		try {
			this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
		} catch (JsonMappingException e) {
			log.warn("No fue posible deserializar la respuesta en un problema", e);
		} catch (JsonProcessingException e) {
			log.warn("No fue posible deserializar la respuesta en un problema", e);
		}
	}

}
