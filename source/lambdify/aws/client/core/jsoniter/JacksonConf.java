package lambdify.aws.client.core.jsoniter;

import java.io.IOException;
import java.util.Date;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 *
 */
@UtilityClass
public class JacksonConf {

	public ObjectMapper DEFAULT_INSTANCE = createDefaultObjectMapper();

	private ObjectMapper createDefaultObjectMapper() {
		val module = new SimpleModule("Lambdify");
		module.addDeserializer( Date.class, new DateCustomDeserializer() );

		return new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(Feature.ALLOW_COMMENTS, true)
			.registerModule( module )
		;
	}

	private class DateCustomDeserializer extends DateDeserializer {

		@Override
		public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			switch (p.getCurrentTokenId()) {
				case JsonTokenId.ID_NUMBER_FLOAT:
					try {
						return new Date( p.getLongValue() );
					} catch ( Throwable cause ) {
						return super.deserialize( p, ctxt );
					}
				default: return super.deserialize( p, ctxt );
			}
		}
	}
}
