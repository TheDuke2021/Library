package com.example.Library.converters;

import com.example.Library.entities.Book;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BookGenreConverter implements AttributeConverter<Book.Genre, String> {

    @Override
    public String convertToDatabaseColumn(Book.Genre attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case ДЕТЕКТИВ:
                return "DETECTIVE";

            case ПРИКЛЮЧЕНИЯ:
                return "ADVENTURE";

            case ФАНТАСТИКА:
                return "FICTION";

            case РОМАН:
                return "ROMANCE";

            case ЮМОР:
                return "HUMOR";

            case КОМИКСЫ:
                return "COMICS";

            case ДЕТСКИЕ_КНИГИ:
                return "CHILDREN";

            case НАУЧНЫЕ_КНИГИ:
                return "SCIENTIFIC";

            case СПРАВОЧНЫЕ_КНИГИ:
                return "REFERENCE";

            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }


    @Override
    public Book.Genre convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case "DETECTIVE":
                return Book.Genre.ДЕТЕКТИВ;

            case "ADVENTURE":
                return Book.Genre.ПРИКЛЮЧЕНИЯ;

            case "FICTION":
                return Book.Genre.ФАНТАСТИКА;

            case "ROMANCE":
                return Book.Genre.РОМАН;

            case "HUMOR":
                return Book.Genre.ЮМОР;

            case "COMICS":
                return Book.Genre.КОМИКСЫ;

            case "CHILDREN":
                return Book.Genre.ДЕТСКИЕ_КНИГИ;

            case "SCIENTIFIC":
                return Book.Genre.НАУЧНЫЕ_КНИГИ;

            case "REFERENCE":
                return Book.Genre.СПРАВОЧНЫЕ_КНИГИ;


            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}