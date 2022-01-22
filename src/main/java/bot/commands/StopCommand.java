package bot.commands;

import bot.model.Person;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import bot.services.SendUserMessageImpl;

public class StopCommand implements Command {

    @Override
    public void req(Person p) {
        var update =p.getUpdate();
        System.out.println("stopCommand");
        var inMessage=update.getMessage();
        SendMessage outMessage=new SendMessage(inMessage.getChatId().toString(),inMessage.getText()+" stopCommand");

        SendUserMessageImpl.sendMessage(outMessage);
    }
}
