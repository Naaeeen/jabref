package org.jabref.logic.filenameformatpattern;

import org.jabref.model.entry.types.EntryType;

public class GlobalFilenamePattern extends AbstractFilenameFormatPatterns {

    public GlobalFilenamePattern(FilenameFormatPattern defaultPattern) {
        this.defaultPattern = defaultPattern;
    }

    public static GlobalFilenamePattern fromPattern(String pattern) {
        return new GlobalFilenamePattern(new FilenameFormatPattern(pattern));
    }

    @Override
    public FilenameFormatPattern getLastLevelFilenameFormatPattern(EntryType key) {
        return defaultPattern;
    }
}
