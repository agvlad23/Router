package bot.commands;

import bot.model.Person;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class StartTaskCommand implements Command{
    @Override
    public void req(Person p) {
        var update =p.getUpdate();
        System.out.println("startTaskCommand");
        var inMessage=update.getMessage();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        var keyboar =new InlineKeyboardButton();
        keyboar.setText("End Task");
        keyboar.setCallbackData("update_end_task");
        rowInline.add(keyboar);
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        SendMessage outMessage=new SendMessage(inMessage.getChatId().toString(),inMessage.getText()+" startTaskCommand");

        outMessage.enableHtml(true);
        outMessage.setReplyMarkup(markupInline);
        outMessage.setText("<b><i> "+inMessage.getText().substring(inMessage.getText().indexOf(' '))+" </i></b>");


        SendUserMessageImpl.sendMessage(outMessage);
    }
}
