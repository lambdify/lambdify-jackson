package lambdify.aws.client.core.jsoniter;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lambdify.aws.client.core.http.AwsClientJsonSerializer;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 */
@Accessors(fluent = true)
public class JacksonAwsClientJsonSerializer implements AwsClientJsonSerializer {

	@Setter
	static ObjectMapper objectMapper = JacksonConf.DEFAULT_INSTANCE;

	@Override
	public String serialize( Object object ) {
		try {
			return objectMapper.writeValueAsString( object );
		} catch ( JsonProcessingException e ) {
			throw new IllegalStateException( e );
		}
	}

	@Override
	public <T> T unserialize(String input, Class<T> clazz) {
		try {
			return objectMapper.readValue( input, clazz );
		} catch ( IOException e ) {
			throw new IllegalStateException( e );
		}
	}
}
