package lambdify.aws.client.core.jsoniter;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambdify.aws.client.core.http.AwsClientJsonSerializer;
import lombok.*;
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

	@Override
	public <T> List<T> unserializeAsList(String input, Class<T> aClass) {
		try {
			val javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, aClass);
			return objectMapper.readValue(input, javaType);
		} catch ( IOException e ) {
			throw new IllegalStateException( e );
		}
	}
}
