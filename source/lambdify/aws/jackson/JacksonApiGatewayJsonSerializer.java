package lambdify.aws.jackson;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.Accessors;

/**
 *
 */
@Getter @Accessors(fluent = true)
public class JacksonApiGatewayJsonSerializer implements lambdify.apigateway.Serializer {

	@Setter static ObjectMapper objectMapper = JacksonConf.DEFAULT_INSTANCE;

	final String contentType = "application/json";

	@Override
	public Stringified toString(Object object) {
		try {
			return Stringified.plainText( objectMapper.writeValueAsString( object ) );
		} catch ( JsonProcessingException e ) {
			throw new IllegalStateException( e );
		}
	}

	@Override
	public <T> T toObject(String input, Class<T> clazz) {
		try {
			return objectMapper.readValue( input, clazz );
		} catch ( IOException e ) {
			throw new IllegalStateException( e );
		}
	}
}
