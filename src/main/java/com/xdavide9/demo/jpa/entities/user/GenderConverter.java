package com.xdavide9.demo.jpa.entities.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender == null ? null : gender.name().toLowerCase();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.valueOf(dbData.toUpperCase());
    }
}
