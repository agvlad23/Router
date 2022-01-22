package bot.bot;

import bot.commands.CommandParser;
import bot.model.Person;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class TrackerBot extends TelegramLongPollingBot {
    protected String botName ;
    protected String botToken;
    public static final String botPrefix="/";

    public TrackerBot()  {
        SendUserMessageImpl.init(this);

        Properties properties = new Properties();

        String rootPath=
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("local.properties")).getPath();
        try {
            properties.load(new FileInputStream(rootPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        botToken = properties.getProperty("Tracker_Token");
        botName = properties.getProperty("Tracker_Name");
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            var role= Role.User;
            var person=new Person(role,update);
            if (messageText.startsWith(botPrefix)){
                CommandParser.getCommandClass(messageText).req(person);
            }

        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();
            String  message_id = update.getCallbackQuery().getMessage().getMessageId().toString();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            System.out.println(update.getCallbackQuery().getMessage());

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";

                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(chat_id);
                new_message.setMessageId(Integer.parseInt(message_id));
                new_message.setText(answer);

                SendUserMessageImpl.sendMessage(new_message);

            }
        }

    }
}
