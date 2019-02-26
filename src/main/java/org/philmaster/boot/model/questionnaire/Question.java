
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
@JsonPropertyOrder({ "rank", "attribute", "isMandatory", "type", "title", "answer" })
public class Question {

	@JsonProperty("rank")
	private String rank;
	@JsonProperty("attribute")
	private String attribute;
	@JsonProperty("isMandatory")
	private String isMandatory;
	@JsonProperty("type")
	private String type;
	@JsonProperty("title")
	private String title;
	@JsonProperty("answer")
	private List<Answer> answer = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("rank")
	public String getRank() {
		return rank;
	}

	@JsonProperty("rank")
	public void setRank(String rank) {
		this.rank = rank;
	}

	@JsonProperty("attribute")
	public String getAttribute() {
		return attribute;
	}

	@JsonProperty("attribute")
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@JsonProperty("isMandatory")
	public String getIsMandatory() {
		return isMandatory;
	}

	@JsonProperty("isMandatory")
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
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
