package kr.co.kjc.externalApi.global.config.jackson.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CommonStringDeserializer extends StdDeserializer<String> {

  public CommonStringDeserializer() {
    super(String.class);
  }

  @Override
  public String deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    return p.getText();  // 항상 String으로 읽기
  }
}
