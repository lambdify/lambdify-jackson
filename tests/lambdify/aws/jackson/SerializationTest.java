package lambdify.aws.jackson;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.util.Date;
import lambdify.aws.events.dynamodb.DynamodbEvent;
import lombok.*;
import org.junit.jupiter.api.*;

/**
 *
 */
class SerializationTest {

	Date expectedDate = new Date( 1529504280 );

	@SneakyThrows
	@Test void canSerializeADynamoDBEvent(){
		val inputFile = readFile( "tests-resources/dynamodb-update.json" );
		val event = JacksonConf.DEFAULT_INSTANCE.readValue( inputFile, DynamodbEvent.class );
		assertNotNull( event );
		assertEquals( expectedDate, event.getRecords().get( 0 ).getDynamodb().getApproximateCreationDateTime() );
	}

	@Test void stressTest(){
		for ( int i = 0; i < 20000; i++ ) {
			canSerializeADynamoDBEvent();
		}
	}

	@SneakyThrows
	String readFile( String fileName ) {
		val bytes = Files.readAllBytes( Paths.get(fileName) );
		return new String(bytes);
	}
}
