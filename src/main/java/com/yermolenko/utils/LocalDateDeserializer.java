package com.yermolenko.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Class LocalDateDeserializer.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    /**
     * Method deserialize.
     *
     * @param jsonParser of type JsonParser
     * @param deserializationContext of type DeserializationContext
     * @return LocalDate
     * @throws IOException when
     * @throws JsonProcessingException when
     */
    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return LocalDate.parse(jsonParser.getText());    }
}
