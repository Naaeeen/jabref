package org.jabref.logic.filenameformatpatterns;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.jabref.model.entry.types.EntryType;

/**
 * A small table, where an entry type is associated with a Filename format pattern.
 * A parent FilenameFormatPattern can be set.
 */
public abstract class AbstractFilenameFormatPatterns {

    protected FilenameFormatPattern defaultPattern = FilenameFormatPattern.NULL_FileName_PATTERN;

    protected Map<EntryType, FilenameFormatPattern> data = new HashMap<>();

    public void addFilenameFormatPattern(EntryType type, String pattern) {
        data.put(type, new FilenameFormatPattern(pattern));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractFilenameFormatPattern{");
        sb.append("defaultPattern=").append(defaultPattern);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        AbstractFilenameFormatPatterns that = (AbstractFilenameFormatPatterns) o;
        return Objects.equals(defaultPattern, that.defaultPattern) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultPattern, data);
    }

    /**
     * Gets an object for a desired key from this FilenameFormatPattern or one of its parents.
     * This method first tries to obtain the object from this FilenameFormatPattern via the
     * <code>get</code> method of <code>Hashtable</code>. If this fails, we try the default.
     * If that fails, we try the parent. If that fails, we return the DEFAULT_FILENAME_FORMAT_PATTERN.
     *
     * @param entryType a <code>EntryType</code>
     * @return the FilenameFormat for the given key.
     */
    public FilenameFormatPattern getValue(EntryType entryType) {
        FilenameFormatPattern result = data.get(entryType);
        if (result == null) {
            result = getDefaultValue();
            if (result == null || FilenameFormatPattern.NULL_FileName_PATTERN.equals(result)) {
                return getLastLevelFilenameFormatPattern(entryType);
            }
        }
        return result;
    }

    /**
     * Checks whether this pattern is customized or the default value.
     */
    public final boolean isDefaultValue(EntryType entryType) {
        return data.get(entryType) == null;
    }

    /**
     * This method is called "...Value" to be in line with the other methods
     *
     * @return null if not available.
     */
    public FilenameFormatPattern getDefaultValue() {
        return this.defaultPattern;
    }

    /**
     * Sets the DEFAULT PATTERN for this key pattern
     *
     * @param bibtexKeyPattern the pattern to store
     */
    public void setDefaultValue(String bibtexKeyPattern) {
        Objects.requireNonNull(bibtexKeyPattern);
        this.defaultPattern = new FilenameFormatPattern(bibtexKeyPattern);
    }

    public Set<EntryType> getAllKeys() {
        return data.keySet();
    }

    public Map<EntryType, FilenameFormatPattern> getPatterns() {
        return data.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public abstract FilenameFormatPattern getLastLevelFilenameFormatPattern(EntryType key);
}
