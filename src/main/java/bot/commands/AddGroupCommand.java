package bot.commands;

import bot.model.Person;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import router.client.Client;

import javax.swing.event.CaretListener;

public class AddGroupCommand implements Command {
    @Override
    public void req(Person p) {
        var update =p.getUpdate();
        System.out.println("addGroupCommand");
        var inMessage=update.getMessage();

        var s=inMessage.getText().split(" ");
        String name="default";
        String color="default";

        if (s.length>1)
            name=s[1];
        if (s.length>2)
            color=s[2];

        Client.addGroup(name,color);


        SendMessage outMessage=new SendMessage(inMessage.getChatId().toString(),inMessage.getText()+" addGroupCommand");

        SendUserMessageImpl.sendMessage(outMessage);
    }
}
