package org.jabref.logic.filenameformatpattern;

public record FilenameFormatPattern(String stringRepresentation) {
    public static final FilenameFormatPattern NULL_FILENAME_FORMAT_PATTERN = new FilenameFormatPattern("");
}
