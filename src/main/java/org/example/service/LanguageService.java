package org.example.service;

import java.util.HashMap;
import java.util.Map;

public class LanguageService {

    private final Map<String, String> languages = new HashMap<>();

    public LanguageService() {

        // Русский
        languages.put("русский", "RU");
        languages.put("russian", "RU");
        languages.put("ru", "RU");

        // Английский
        languages.put("английский", "EN");
        languages.put("english", "EN");
        languages.put("en", "EN");

        // Белорусский
        languages.put("белорусский", "BE");
        languages.put("белорусский язык", "BE");
        languages.put("belarusian", "BE");
        languages.put("be", "BE");

        // Немецкий
        languages.put("немецкий", "DE");
        languages.put("german", "DE");
        languages.put("de", "DE");

        // Французский
        languages.put("французский", "FR");
        languages.put("french", "FR");
        languages.put("fr", "FR");

        // Украинский
        languages.put("украинский", "UK");
        languages.put("ukrainian", "UK");
        languages.put("uk", "UK");

        // Литовский
        languages.put("литовский", "LT");
        languages.put("lithuanian", "LT");
        languages.put("lt", "LT");

        // Польский
        languages.put("польский", "PL");
        languages.put("polish", "PL");
        languages.put("pl", "PL");

        // Латышский
        languages.put("латышский", "LV");
        languages.put("latvian", "LV");
        languages.put("lv", "LV");

        // Итальянский
        languages.put("итальянский", "IT");
        languages.put("italian", "IT");
        languages.put("it", "IT");

        // Сербский
        languages.put("сербский", "SR");
        languages.put("serbian", "SR");
        languages.put("sr", "SR");

        // Хорватский
        languages.put("хорватский", "HR");
        languages.put("croatian", "HR");
        languages.put("hr", "HR");

        // Словацкий
        languages.put("словацкий", "SK");
        languages.put("slovak", "SK");
        languages.put("sk", "SK");

        // Молдавский
        languages.put("молдавский", "MO");
        languages.put("moldovan", "MO");

        // Румынский
        languages.put("румынский", "RO");
        languages.put("romanian", "RO");
        languages.put("ro", "RO");

        // Венгерский
        languages.put("венгерский", "HU");
        languages.put("hungarian", "HU");
        languages.put("hu", "HU");

        // Казахский
        languages.put("казахский", "KK");
        languages.put("kazakh", "KK");
        languages.put("kk", "KK");

        // Грузинский
        languages.put("грузинский", "KA");
        languages.put("georgian", "KA");
        languages.put("ka", "KA");

        // Китайский
        languages.put("китайский", "ZH");
        languages.put("chinese", "ZH");
        languages.put("zh", "ZH");

        // Арабский
        languages.put("арабский", "AR");
        languages.put("arabic", "AR");
        languages.put("ar", "AR");

        // Болгарский
        languages.put("болгарский", "BG");
        languages.put("bulgarian", "BG");
        languages.put("bg", "BG");

        // Турецкий
        languages.put("турецкий", "TR");
        languages.put("turkish", "TR");
        languages.put("tr", "TR");

        // Испанский
        languages.put("испанский", "ES");
        languages.put("spanish", "ES");
        languages.put("es", "ES");

        // Португальский
        languages.put("португальский", "PT");
        languages.put("portuguese", "PT");
        languages.put("pt", "PT");

        // Чешский
        languages.put("чешский", "CS");
        languages.put("czech", "CS");
        languages.put("cs", "CS");

        // Узбекский
        languages.put("узбекский", "UZ");
        languages.put("uzbek", "UZ");
        languages.put("uz", "UZ");

        // Армянский
        languages.put("армянский", "HY");
        languages.put("armenian", "HY");
        languages.put("hy", "HY");

        // Азербайджанский
        languages.put("азербайджанский", "AZ");
        languages.put("azerbaijani", "AZ");
        languages.put("az", "AZ");

        // Нидерландский
        languages.put("нидерландский", "NL");
        languages.put("dutch", "NL");
        languages.put("nl", "NL");

        // Иврит
        languages.put("иврит", "HE");
        languages.put("hebrew", "HE");
        languages.put("he", "HE");

        // Эстонский
        languages.put("эстонский", "ET");
        languages.put("estonian", "ET");
        languages.put("et", "ET");

        // Греческий
        languages.put("греческий", "EL");
        languages.put("greek", "EL");
        languages.put("el", "EL");

        // Туркменский
        languages.put("туркменский", "TK");
        languages.put("turkmen", "TK");
        languages.put("tk", "TK");

        // Персидский
        languages.put("персидский", "FA");
        languages.put("persian", "FA");
        languages.put("fa", "FA");

        // Японский
        languages.put("японский", "JA");
        languages.put("japanese", "JA");
        languages.put("ja", "JA");

        // Вьетнамский
        languages.put("вьетнамский", "VI");
        languages.put("vietnamese", "VI");
        languages.put("vi", "VI");

        // Норвежский
        languages.put("норвежский", "NO");
        languages.put("norwegian", "NO");
        languages.put("no", "NO");

        // Шведский
        languages.put("шведский", "SV");
        languages.put("swedish", "SV");
        languages.put("sv", "SV");
    }

    public String findLanguageCode(String input) {

        String normalized = input
                .toLowerCase()
                .trim()
                .replace("ё", "е");

        return languages.get(normalized);
    }
}
