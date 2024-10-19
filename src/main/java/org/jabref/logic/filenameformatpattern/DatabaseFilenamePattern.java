package org.jabref.logic.filenameformatpattern;

import org.jabref.model.entry.types.EntryType;

public class DatabaseFilenamePattern extends AbstractFilenameFormatPatterns {

    private final GlobalFilenamePattern globalFilenamePattern;

    public DatabaseFilenamePattern(GlobalFilenamePattern globalFilenamePattern) {
        this.globalFilenamePattern = globalFilenamePattern;
    }

    @Override
    public FilenameFormatPattern getLastLevelFilenameFormatPattern(EntryType key) {
        return globalFilenamePattern.getValue(key);
    }
}
