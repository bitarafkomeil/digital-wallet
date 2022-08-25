package com.leovegas.digital.wallet.data.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.leovegas.digital.wallet.data.enumeration.TransactionType;

import java.io.IOException;

public class TransactionDeserializer extends JsonDeserializer<TransactionType> {
    @Override
    public TransactionType deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String input = parser.getText().trim();
        return TransactionType.find(input);
    }
}