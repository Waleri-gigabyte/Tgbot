package org.example.handler;


public class BotHandler {

    private final StepHandler stepHandler;

    public BotHandler(
            StepHandler stepHandler
    ) {
        this.stepHandler = stepHandler;
    }

    //------------

    public void handle(Long chatId, String text) {

        switch (text) {
            case "/start" -> stepHandler.handleStart(chatId);
            case "/help" -> stepHandler.handleHelp(chatId);
            default -> stepHandler.handleCurrentStep(chatId, text);
        }
    }


}
