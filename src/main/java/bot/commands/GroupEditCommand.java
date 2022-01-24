package bot.commands;

import bot.model.Person;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import router.client.Client;
import router.client.generated.User;
import router.client.generated.UserGroup;

import java.util.ArrayList;
import java.util.List;

public class GroupEditCommand implements Command {
    @Override
    public void req(Person p) {
        if(p.getRole()== Role.ADMIN||p.getRole()==Role.TEACHER){
            var update = p.getUpdate();
            System.out.println("group Edit command");

            var inMessage = update.getMessage();

            SendMessage outMessage = new SendMessage(inMessage.getChatId().toString(), inMessage.getText() + " helpCommand");

            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            List<UserGroup> groupList = Client.getGroups();//new ArrayList<>();
            int counter = 0;
            int counterLines = 0;
            for (var el : groupList) {

                var button = new InlineKeyboardButton();
                String group = "";
                if (el.getColorMark() != null)
                    group = el.getColorMark();
                button.setText(el.getGroupName()+ " " + group);
                button.setCallbackData(counterLines + "_" + counter + "_deleteGroup_update_selected_groupName_" + el.getGroupName());
                rowInline.add(button);
                counter++;
                if (counter > 2) {
                    rowsInline.add(rowInline);
                    rowInline = new ArrayList<>();
                    counter = 0;
                    counterLines++;
                }
            }

            List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
            var button = new InlineKeyboardButton();
            button.setText("delete them");
            button.setCallbackData("update_deleteGroup_selected");
            rowInline3.add(button);

            rowsInline.add(rowInline);
            rowsInline.add(rowInline3);

            markupInline.setKeyboard(rowsInline);


            outMessage.enableHtml(true);
            outMessage.setReplyMarkup(markupInline);
            outMessage.setText("<b><i> "+" Group Delete Command </i></b>");
            SendUserMessageImpl.sendMessage(outMessage);

        }else {
            var k = new SendMessage();
            k.setChatId(p.getUpdate().getMessage().getChatId().toString());
            k.setText("You are not allowed to see list of users");
            SendUserMessageImpl.sendMessage(k);
        }

    }
}
