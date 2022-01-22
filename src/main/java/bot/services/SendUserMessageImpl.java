package bot.services;

import bot.bot.TrackerBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SendUserMessageImpl {
    protected static TelegramLongPollingBot teleBot;
    private static final Logger LOGGER = Logger.getLogger(SendUserMessageImpl.class.getName());




    public static void sendMessage(BotApiMethod<?> message) {
            try {
                teleBot.execute(message);
            } catch (TelegramApiException e) {
                LOGGER.log(Level.SEVERE,"TELegram !! "+e );
                System.out.println("Errrr");
                e.printStackTrace();
            }



    }

    public static void init(TrackerBot trackerBot) {
        if (teleBot == null)
            teleBot = trackerBot;
    }
}
