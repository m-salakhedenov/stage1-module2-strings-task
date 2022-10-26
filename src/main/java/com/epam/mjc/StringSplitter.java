package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        return splitStringCollectionByDelimiters(List.of(source), new ArrayDeque<>(delimiters));
    }

    private List<String> splitStringCollectionByDelimiters(
            List<String> stringsToSplit,
            Queue<String> delimiters
    ) {
        if (delimiters.isEmpty()) {
            return stringsToSplit;
        }

        String delimiter = delimiters.remove();
        List<String> tokens = new ArrayList<>();

        for (String toSplit : stringsToSplit) {
            String[] splitResult = toSplit.split(delimiter);

            for (String token : splitResult) {
                if (!token.isBlank()) {
                    tokens.add(token);
                }
            }
        }

        return splitStringCollectionByDelimiters(tokens, delimiters);
    }

}
