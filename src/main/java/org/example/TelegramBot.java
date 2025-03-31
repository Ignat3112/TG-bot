package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private static String instruction = "Веди себя как Иосиф Виссарионович Сталин используя его выражиния и слова. Вопрос пользователя: ";
    private OpenRouter LLM;
    public TelegramBot(String botUsername, String botToken) {
        String apikey = "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";
        String model = "deepseek/deepseek-chat-v3-0324:free";
        LLM = new OpenRouter(apikey, model);
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            String query = instruction + messageText;
            String answer = "";
            try {
                answer = LLM.sendQuestion(query);
            } catch (Exception exception){

            };
            message.setText(answer);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}