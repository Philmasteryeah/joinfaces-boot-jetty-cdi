
package org.philmaster.boot.model.questionnaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "@attributes", "answer" })
public class Question {

	@JsonProperty("@attributes")
	private Attributes_ attributes;
	@JsonProperty("answer")
	private List<Answer> answer = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("@attributes")
	public Attributes_ getAttributes() {
		return attributes;
	}

	@JsonProperty("@attributes")
	public void setAttributes(Attributes_ attributes) {
		this.attributes = attributes;
	}

	@JsonProperty("answer")
	public List<Answer> getAnswer() {
		return answer;
	}

	@JsonProperty("answer")
	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
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
