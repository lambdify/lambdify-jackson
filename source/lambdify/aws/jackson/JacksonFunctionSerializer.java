package lambdify.aws.jackson;

import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambdify.core.FunctionSerializer;
import lombok.*;
import lombok.experimental.Accessors;

/**
 *
 */
@Accessors(fluent = true)
public class JacksonFunctionSerializer implements FunctionSerializer {

	@Setter
	static ObjectMapper objectMapper = JacksonConf.DEFAULT_INSTANCE;

	@Override
	public void serialize(Object object, OutputStream outputStream) throws IOException {
		objectMapper.writeValue( outputStream, object );
	}

	@Override
	public <T> T deserialize(InputStream inputStream, Class<T> clazz) throws IOException {
		return objectMapper.readValue( inputStream, clazz );
	}
}
