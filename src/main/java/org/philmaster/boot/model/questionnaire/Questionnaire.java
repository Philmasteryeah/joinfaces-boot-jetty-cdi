
package org.philmaster.boot.model.questionnaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "@attributes", "question" })
public class Questionnaire {

	@JsonProperty("@attributes")
	private Attributes attributes;
	@JsonProperty("question")
	private List<Question> question = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("@attributes")
	public Attributes getAttributes() {
		return attributes;
	}

	@JsonProperty("@attributes")
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	@JsonProperty("question")
	public List<Question> getQuestion() {
		return question;
	}

	@JsonProperty("question")
	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
