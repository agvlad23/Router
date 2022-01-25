package bot.commands;

import bot.model.Person;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import router.client.Client;

import java.util.ArrayList;
import java.util.List;

public class UserListCommand implements Command{
    @Override
    public void req(Person p) {
        if (p.getRole()== Role.USER||p.getRole()==Role.TEAM_LEAD){
            var k=new SendMessage();
            k.setChatId(p.getUpdate().getMessage().getChatId().toString());
            if(p.getUser().getUserGroup()==null)
                k.setText("You are not allowed to see list of users");
            else {
                StringBuilder s= new StringBuilder();
                var tempUsers=Client.getUsersFromGroup(p.getUser().getUserGroup().getGroupName());
                for (var el:tempUsers
                     ) {
                    s.append(el.getUsername()).append(" ").append(el.getUserRole()).append("\n");
                }
                k.setText(s.toString());

            }
            SendUserMessageImpl.sendMessage(k);
        }else{
            var inMessage = p.getUpdate().getMessage();

            SendMessage outMessage = new SendMessage(inMessage.getChatId().toString(), inMessage.getText() + " helpCommand");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            int counter = 0;
            int counterLines = 0;
            var userList=Client.getAllUsers();
            for (var el : userList) {

                var button = new InlineKeyboardButton();
                String group = "NONE";
                if (el.getUserGroup() != null)
                    group = el.getUserGroup().getGroupName();
                button.setText(el.getUsername() + " " + group);
                button.setCallbackData(counterLines + "_" + counter + "_update_selected_telegramID_" + el.getTelegramUserId());
                rowInline.add(button);
                counter++;
                if (counter > 4) {
                    rowsInline.add(rowInline);
                    rowInline = new ArrayList<>();
                    counter = 0;
                    counterLines++;
                }
            }
            rowsInline.add(rowInline);


            markupInline.setKeyboard(rowsInline);


            outMessage.enableHtml(true);
            outMessage.setReplyMarkup(markupInline);
            outMessage.setText("<b><i> "+"List Command </i></b>");

            SendUserMessageImpl.sendMessage(outMessage);
        }
    }
}
