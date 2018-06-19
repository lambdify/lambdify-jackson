package lambdify.aws.client.core.jsoniter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;

/**
 *
 */
public interface JacksonConf {

	ObjectMapper DEFAULT_INSTANCE = createDefaultObjectMapper();

	static ObjectMapper createDefaultObjectMapper() {
		return new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
			.setSerializationInclusion(Include.NON_NULL)
		;
	}

}
