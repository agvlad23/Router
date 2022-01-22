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


        var inMessage=update.getMessage();

        SendMessage outMessage=new SendMessage(inMessage.getChatId().toString(),inMessage.getText()+" helpCommand");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        var keyboar =new InlineKeyboardButton();
        keyboar.setText("Update message text");
        keyboar.setCallbackData("update_msg_text");
        rowInline.add(keyboar);

        keyboar =new InlineKeyboardButton();
        keyboar.setText("second button");
        keyboar.setCallbackData("update_msg_text");
        rowInline.add(keyboar);
        keyboar =new InlineKeyboardButton();
        keyboar.setText("<b>bold text</b>");
        keyboar.setCallbackData("update_msg_text");
        rowInline.add(keyboar);
        keyboar =new InlineKeyboardButton();
        keyboar.setText("_italic text_");
        keyboar.setCallbackData("update_msg_text");
        rowInline.add(keyboar);
        rowsInline.add(rowInline);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);


        outMessage.enableHtml(true);
        outMessage.setReplyMarkup(markupInline);
        outMessage.setText("<b><i> "+inMessage.getText()+" helpCommand </i></b>");

        SendUserMessageImpl.sendMessage(outMessage);
    }
}
