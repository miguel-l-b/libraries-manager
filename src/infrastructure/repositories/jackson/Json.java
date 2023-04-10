package infrastructure.repositories.jackson;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class Json {
    public static String toJson(Object objeto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter jsonValue = new StringWriter();
        mapper.writeValue(new PrintWriter(jsonValue), objeto);
        return jsonValue.toString();
    }

    public static Object fromJson(String json, Class objectClass) throws Exception {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(json);
        Object obj = jp.readValueAs(objectClass);
        return obj;
    } 
}
