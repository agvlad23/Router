package bot.commands;

import bot.model.Person;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import router.client.Client;
import router.client.generated.*;
import java.util.ArrayList;
import java.util.List;

public class InterfaceCommand implements Command{
    @Override
    public void req(Person p) {
        var update =p.getUpdate();
        System.out.println("interface command");

        var inMessage=update.getMessage();

        SendMessage outMessage=new SendMessage(inMessage.getChatId().toString(),inMessage.getText()+" helpCommand");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        List<User>userList= Client.getUsers();//new ArrayList<>();


/*        for (int i=0;i<33;i++) {
            var user = new User();
            user.setUserId("UserId "+i);
            user.setName("UserName"+i);
            user.setGroup(""+i);

            userList.add(user);
        }*/
        int counter=0;
        int counterLines=0;
        for (var el:userList) {

            var button=new InlineKeyboardButton();
            button.setText(el.getUsername()+" "+el.getUserGroup());
            button.setCallbackData(counterLines+"_"+counter+"_update_selected");
            rowInline.add(button);
            counter++;
            if (counter>4){
                rowsInline.add(rowInline);
                rowInline=new ArrayList<>();
                counter=0;
                counterLines++;
            }
        }






        var groups=Client.getGroups();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        for (var group: groups
             ) {


            var button=new InlineKeyboardButton();
            button.setText("move them to group "+ group);
            button.setCallbackData(group+"_update_move_selected");
            rowInline2.add(button);

        }

        List<InlineKeyboardButton>  rowInline3 = new ArrayList<>();
        var button=new InlineKeyboardButton();
        button.setText("delete them");
        button.setCallbackData("update_delete_selected");
        rowInline3.add(button);

        rowsInline.add(rowInline);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);

        markupInline.setKeyboard(rowsInline);


        outMessage.enableHtml(true);
        outMessage.setReplyMarkup(markupInline);
        outMessage.setText("<b><i> "+inMessage.getText()+" interface Command </i></b>");

        SendUserMessageImpl.sendMessage(outMessage);
    }
}
