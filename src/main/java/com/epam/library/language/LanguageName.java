package com.epam.library.language;

import java.util.HashSet;
import java.util.Set;

public class LanguageName {
    private Set<String> languages = new HashSet<>();

    public LanguageName() {
        languages.add("en_GB");
        languages.add("ru_RU");
        languages.add("be_BY");
    }

    public boolean isLangExist(String string) {
        return languages.contains(string);
    }
}
