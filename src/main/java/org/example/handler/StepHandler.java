package org.example.handler;

import org.example.service.CalculationService;
import org.example.service.CalculationResult;
import org.example.service.LanguageService;
import org.example.session.SessionStorage;
import org.example.session.Step;
import org.example.session.UserSession;
import org.example.telegram.KeyboardFactory;
import org.example.telegram.MessageSender;

public class StepHandler {


    private final SessionStorage sessionStorage;
    private final LanguageService languageService;
    private final MessageSender messageSender;
    private final KeyboardFactory keyboardFactory;
    private final CalculationService calculationService;

    public StepHandler(
            SessionStorage sessionStorage,
            LanguageService languageService,
            MessageSender messageSender,
            KeyboardFactory keyboardFactory,
            CalculationService calculationService
    ) {
        this.sessionStorage = sessionStorage;
        this.languageService = languageService;
        this.messageSender = messageSender;
        this.keyboardFactory = keyboardFactory;
        this.calculationService = calculationService;
    }

    //------------

    public void handleCurrentStep(Long chatId, String text) {

        UserSession session = sessionStorage.getSession(chatId);

        switch (session.getStep()) {

            case CHOOSE_DIRECTION ->
                    handleDirection(chatId, text, session);

            case CHOOSE_SOURCE_LANGUAGE ->
                    handleLanguageSearch(chatId, text, session);

            case CHOOSE_TARGET_LANGUAGE ->
                    handleTargetLanguage(chatId, text, session);

            case CHOOSE_DOCUMENT_TYPE ->
                    handleDocumentType(chatId, text, session);

            case ENTER_PAGES ->
                    handlePages(chatId, text, session);

            case CHOOSE_URGENCY ->
                    handleUrgency(chatId, text, session);

            case CHOOSE_TRANSLATOR_SIGNATURE ->
                    handleTranslatorSignature(chatId, text, session);

            case CHOOSE_NOTARY_COPY ->
                    handleNotaryCopy(chatId, text, session);

            case CHOOSE_APOSTILLE ->
                    handleApostille(chatId, text, session);

            case CHOOSE_LEGALIZATION ->
                    handleLegalization(chatId, text, session);

            case CHOOSE_LEGALIZATION_DOCUMENT ->
                    handleLegalizationDocument(chatId, text, session);

            case CHOOSE_LEGALIZATION_URGENCY ->
                    handleLegalizationUrgency(chatId, text, session);

            case RESULT ->
                    handleResult(chatId, text, session);

            default ->
                    messageSender.sendMessage(chatId, "Не понимаю эту команду.");
        }
    }

    public void handleStart(Long chatId) {

        sessionStorage.resetSession(chatId);

        UserSession session = sessionStorage.getSession(chatId);
        session.setStep(Step.CHOOSE_DIRECTION);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Привет! Я помогу рассчитать стоимость перевода.\n" +
                        "Выберите направление перевода.",
                keyboardFactory.createDirectionKeyboard()
        );
    }

    public void handleHelp(Long chatId) {
        messageSender.sendMessage(chatId, "Помощь");
    }

    private void handleLanguageSearch(
            Long chatId,
            String text,
            UserSession session
    ) {

        String languageCode = languageService.findLanguageCode(text);

        if (languageCode == null) {
            messageSender.sendMessage(
                    chatId,
                    "Я не нашёл такой язык. Попробуйте написать название языка ещё раз."
            );
            return;
        }

        session.setSourceLanguage(languageCode);
        session.setStep(Step.CHOOSE_TARGET_LANGUAGE);

        messageSender.sendMessage(
                chatId,
                "Язык документа: " + languageCode +
                        "\nТеперь укажите, на какой язык нужно перевести документ."
        );
    }

    private void handleTargetLanguage(
            Long chatId,
            String text,
            UserSession session
    ) {

        String languageCode = languageService.findLanguageCode(text);

        if (languageCode == null) {
            messageSender.sendMessage(
                    chatId,
                    "Я не нашёл такой язык. Попробуйте написать название языка ещё раз."
            );
            return;
        }

        session.setTargetLanguage(languageCode);
        session.setStep(Step.CHOOSE_DOCUMENT_TYPE);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Направление перевода выбрано: "
                        + session.getSourceLanguage()
                        + " → "
                        + session.getTargetLanguage(),
                keyboardFactory.createDocumentTypeKeyboard()
        );
    }


    private void handleUrgency(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Обычный перевод" ->
                    session.setUrgentTranslation(false);

            case "Срочный перевод +50%" ->
                    session.setUrgentTranslation(true);

            case "⬅️ Назад" -> {
                session.setStep(Step.ENTER_PAGES);
                messageSender.sendMessage(
                        chatId,
                        "Укажите количество страниц в документе:"
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите срочность с помощью кнопок."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_TRANSLATOR_SIGNATURE);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Нужно ли нотариальное удостоверение подписи переводчика?",
                keyboardFactory.createYesNoKeyboard()
        );
    }



    private void handleDocumentType(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Личный документ" ->
                    session.setDocumentType("PERSONAL");

            case "Иной документ" ->
                    session.setDocumentType("OTHER");

            case "Сложный текст" ->
                    session.setDocumentType("COMPLEX");

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_TARGET_LANGUAGE);
                messageSender.sendMessage(
                        chatId,
                        "Укажите, на какой язык нужно перевести документ:"
                );
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите тип документа с помощью кнопок."
                );
                return;
            }
        }

        session.setStep(Step.ENTER_PAGES);

        messageSender.sendMessage(
                chatId,
                "Укажите количество страниц в документе:"
        );
    }

    private void handlePages(
            Long chatId,
            String text,
            UserSession session
    ) {

        int pages;

        try {
            pages = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            messageSender.sendMessage(
                    chatId,
                    "Введите количество страниц числом. Например: 3"
            );
            return;
        }

        if (pages <= 0) {
            messageSender.sendMessage(
                    chatId,
                    "Количество страниц должно быть больше 0."
            );
            return;
        }

        session.setPages(pages);
        session.setStep(Step.CHOOSE_URGENCY);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Количество страниц: " + session.getPages()
                        + "\n\nВыберите срочность перевода:",
                keyboardFactory.createUrgencyKeyboard()
        );
    }

    private void handleDirection(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "RU → EN" -> {
                session.setSourceLanguage("RU");
                session.setTargetLanguage("EN");
            }

            case "EN → RU" -> {
                session.setSourceLanguage("EN");
                session.setTargetLanguage("RU");
            }

            case "RU → PL" -> {
                session.setSourceLanguage("RU");
                session.setTargetLanguage("PL");
            }

            case "PL → RU" -> {
                session.setSourceLanguage("PL");
                session.setTargetLanguage("RU");
            }

            case "RU → DE" -> {
                session.setSourceLanguage("RU");
                session.setTargetLanguage("DE");
            }

            case "DE → RU" -> {
                session.setSourceLanguage("DE");
                session.setTargetLanguage("RU");
            }

            case "🔎 Найти язык" -> {
                session.setStep(Step.CHOOSE_SOURCE_LANGUAGE);
                messageSender.sendMessage(chatId, "Введите название языка, на котором написан ваш документ:");
                return;
            }


            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите направление с помощью кнопок."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_DOCUMENT_TYPE);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Вы выбрали: "
                        + session.getSourceLanguage()
                        + " → "
                        + session.getTargetLanguage(),
                keyboardFactory.createDocumentTypeKeyboard()
        );
    }

    private void handleTranslatorSignature(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Да" ->
                    session.setTranslatorSignature(true);

            case "Нет" ->
                    session.setTranslatorSignature(false);

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_URGENCY);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Выберите срочность перевода:",
                        keyboardFactory.createUrgencyKeyboard()
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите Да или Нет."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_NOTARY_COPY);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Нужно ли нотариальное заверение копии документа?",
                keyboardFactory.createYesNoKeyboard()
        );
    }

    private void handleNotaryCopy(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Да" ->
                    session.setNotaryCopy(true);

            case "Нет" ->
                    session.setNotaryCopy(false);

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_TRANSLATOR_SIGNATURE);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Нужно ли нотариальное удостоверение подписи переводчика?",
                        keyboardFactory.createYesNoKeyboard()
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите Да или Нет."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_APOSTILLE);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Нужен ли апостиль?",
                keyboardFactory.createApostilleKeyboard()
        );
    }

    private void handleApostille(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Апостиль не нужен" ->
                    session.setApostilleType("NONE");

            case "Обычный апостиль" ->
                    session.setApostilleType("REGULAR");

            case "Срочный апостиль" ->
                    session.setApostilleType("URGENT");

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_NOTARY_COPY);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Нужно ли нотариальное заверение копии документа?",
                        keyboardFactory.createYesNoKeyboard()
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите вариант с помощью кнопок."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_LEGALIZATION);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Нужна ли легализация документа?",
                keyboardFactory.createYesNoKeyboard()
        );
    }

    private void handleLegalization(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Да" -> {
                session.setLegalization(true);
                session.setStep(Step.CHOOSE_LEGALIZATION_DOCUMENT);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Выберите тип документа для легализации:",
                        keyboardFactory.createLegalizationDocumentKeyboard()
                );
            }

            case "Нет" -> {
                session.setLegalization(false);
                showResult(chatId, session);
            }

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_APOSTILLE);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Нужен ли апостиль?",
                        keyboardFactory.createApostilleKeyboard()
                );
            }

            case "❌ Начать заново" ->
                    handleStart(chatId);

            default ->
                    messageSender.sendMessage(
                            chatId,
                            "Пожалуйста, выберите Да или Нет."
                    );
        }
    }

    private void handleLegalizationDocument(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Документ об образовании" ->
                    session.setLegalizationDocumentType("EDUCATION");

            case "Нотариальный / судебный документ" ->
                    session.setLegalizationDocumentType("NOTARY");

            case "Свидетельство о рождении / справка о несудимости" ->
                    session.setLegalizationDocumentType("ORIGINAL");

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_LEGALIZATION);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Нужна ли легализация документа?",
                        keyboardFactory.createYesNoKeyboard()
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите тип документа с помощью кнопок."
                );
                return;
            }
        }

        session.setStep(Step.CHOOSE_LEGALIZATION_URGENCY);

        messageSender.sendMessageWithKeyboard(
                chatId,
                "Выберите срок легализации через МИД:",
                keyboardFactory.createLegalizationUrgencyKeyboard()
        );
    }

    private void handleLegalizationUrgency(
            Long chatId,
            String text,
            UserSession session
    ) {

        switch (text) {

            case "Обычная — около 1,5 месяца" ->
                    session.setUrgentLegalization(false);

            case "Срочная — около 7 дней" ->
                    session.setUrgentLegalization(true);

            case "⬅️ Назад" -> {
                session.setStep(Step.CHOOSE_LEGALIZATION_DOCUMENT);

                messageSender.sendMessageWithKeyboard(
                        chatId,
                        "Выберите тип документа для легализации:",
                        keyboardFactory.createLegalizationDocumentKeyboard()
                );
                return;
            }

            case "❌ Начать заново" -> {
                handleStart(chatId);
                return;
            }

            default -> {
                messageSender.sendMessage(
                        chatId,
                        "Пожалуйста, выберите срок с помощью кнопок."
                );
                return;
            }
        }

        showResult(chatId, session);
    }

    private void showResult(
            Long chatId,
            UserSession session
    ) {

        session.setStep(Step.CONFIRM_CALCULATION);

        String result =
                "Пожалуйста, проверьте данные:\n\n"
                        + "Направление: "
                        + session.getSourceLanguage()
                        + " → "
                        + session.getTargetLanguage()
                        + "\n"
                        + "Тип документа: "
                        + session.getDocumentType()
                        + "\n"
                        + "Количество страниц: "
                        + session.getPages()
                        + "\n"
                        + "Срочный перевод: "
                        + (session.isUrgentTranslation() ? "Да" : "Нет")
                        + "\n"
                        + "Удостоверение подписи переводчика: "
                        + (session.isTranslatorSignature() ? "Да" : "Нет")
                        + "\n"
                        + "Нотариальное заверение копии: "
                        + (session.isNotaryCopy() ? "Да" : "Нет")
                        + "\n"
                        + "Апостиль: "
                        + session.getApostilleType()
                        + "\n"
                        + "Легализация: "
                        + (session.isLegalization() ? "Да" : "Нет")
                        + "\n"
                        + "Всё ли верно указано?";

        messageSender.sendMessageWithKeyboard(
                chatId,
                result,
                keyboardFactory.createYesNoKeyboard()
        );
    }

    private void handleResult(
            Long chatId,
            String text,
            UserSession session
    ) {

        if (text.equals("❌ Начать заново")) {
            handleStart(chatId);
            return;
        }

        session.setStep(Step.RESULT);

        CalculationResult calculationResult =
                calculationService.calculate(session);

        String result =
                "Расчёт стоимости:\n\n"
                        + "Направление: "
                        + session.getSourceLanguage()
                        + " → "
                        + session.getTargetLanguage()
                        + "\n"
                        + "Количество страниц: "
                        + session.getPages()
                        + "\n\n"

                        + "Перевод: "
                        + calculationResult.getTranslationPrice()
                        + " руб.\n"

                        + "Сложность: +"
                        + calculationResult.getComplexitySurcharge()
                        + " руб.\n"

                        + "Срочность: +"
                        + calculationResult.getUrgencySurcharge()
                        + " руб.\n"

                        + "Удостоверение подписи переводчика: +"
                        + calculationResult.getTranslatorSignaturePrice()
                        + " руб.\n"

                        + "Нотариальное заверение копии: +"
                        + calculationResult.getNotaryCopyPrice()
                        + " руб.\n"

                        + "Апостиль: +"
                        + calculationResult.getApostillePrice()
                        + " руб.\n"

                        + "Легализация: +"
                        + calculationResult.getLegalizationPrice()
                        + " руб.\n\n"

                        + "Итого: "
                        + calculationResult.getTotalPrice()
                        + " руб.";

        messageSender.sendMessage(chatId, result);
    }

}
