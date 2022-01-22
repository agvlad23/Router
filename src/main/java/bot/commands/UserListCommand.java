package bot.commands;

import bot.model.Person;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import router.client.Client;

public class UserListCommand implements Command{
    @Override
    public void req(Person p) {
        if (p.getRole()== Role.User){
            var k=new SendMessage();
            k.setChatId(p.getUpdate().getMessage().getChatId().toString());
            k.setText("You are not allowed to see list of users");
            SendUserMessageImpl.sendMessage(k);
        }else{
            Client.getAllUsers();
        }
    }
}
