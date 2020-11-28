package com.cts.crisp;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

public class CustomDateDeserializer extends DateDeserializer {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
     public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
         // get the value from the JSON
         long timeInMilliseconds = Long.parseLong(jsonParser.getText());

         Calendar calendar = Calendar.getInstance();
         calendar.setTimeInMillis(timeInMilliseconds);
         return calendar.getTime();
     }
 }