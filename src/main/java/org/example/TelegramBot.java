package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private static String instruction = "prompt";
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