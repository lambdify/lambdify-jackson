package lambdify.aws.jackson;

import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lambdify.core.AwsFunctionSerializer;
import lombok.*;
import lombok.experimental.Accessors;

/**
 *
 */
@Accessors(fluent = true)
public class JacksonAwsFunctionSerializer implements AwsFunctionSerializer {

	@Setter
	static ObjectMapper objectMapper = JacksonConf.DEFAULT_INSTANCE;

	@Override
	public byte[] serialize(Object object) throws IOException {
		val bytes = new ByteArrayOutputStream();
		objectMapper.writeValue( bytes, object );
		return bytes.toByteArray();
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
		return objectMapper.readValue( bytes, clazz );
	}
}
