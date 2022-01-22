package bot.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SendUserMessage {
    void sendMessage(String chatID,String message );
}
