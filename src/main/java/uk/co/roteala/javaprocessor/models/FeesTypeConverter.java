package uk.co.roteala.javaprocessor.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FeesTypeConverter implements AttributeConverter<FeesType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FeesType type) {
        if (type == null) {
            return null;
        }

        return type.getValue();
    }

    @Override
    public FeesType convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }

        return FeesType.getFeesType(integer);
    }
}
