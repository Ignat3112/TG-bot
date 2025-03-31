package org.example;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws Exception {
        String botToken = "7703749149:AAGFgptKTsyI1u1iJBt7twTXkLn62eR3tgU";
        String botName = "Java2034bot";
        TelegramBot bot = new TelegramBot(botName, botToken);
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
    }
}