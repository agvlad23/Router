package bot.commands;

import bot.model.Person;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import bot.services.SendUserMessageImpl;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command{
    @Override
    public void req(Person p) {
        var update =p.getUpdate();
        System.out.println("helpCommand");


        var k = new SendMessage();
        k.enableHtml(true);
        k.setChatId(p.getUpdate().getMessage().getChatId().toString());
        k.setText(
                "<b><i> /help </i></b> command to see this message\n" +
                "<b><i> /start </i></b> command to register\n" +
                "<b><i> /task </i></b> command to start task\n" +
                "<b><i> /list </i></b> command to get list of students\n" +
                "<b><i> /updu </i></b> command to update user role and user groups\n" +
                "<b><i> /updg </i></b> command to update groups\n"+
                "<b><i> /addg </i></b> command to add groups\n"+
                "<b><i> /tracking </i></b> command to see all tracking for today\n");
        SendUserMessageImpl.sendMessage(k);


    }
}
