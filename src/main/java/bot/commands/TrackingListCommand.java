package bot.commands;

import bot.model.Person;
import bot.roles.Role;
import bot.services.SendUserMessageImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import router.client.Client;
import router.client.RestClient;

public class TrackingListCommand implements Command {
    @Override
    public void req(Person p) {
        var k = new SendMessage();
        k.setChatId(p.getUpdate().getMessage().getChatId().toString());

        if (p.getRole()== Role.ADMIN||p.getRole()==Role.TEACHER){
            StringBuilder s= new StringBuilder();
            var tempUsers= RestClient.getLatestTracking();
            for (var user:tempUsers
            ) {
                s.append("<b>").append(user.getName()).append("</b>\n").append(user.getTracking().toString()).append("\n");
            }
            k.setText(s.toString());

        }else{
            k.setText("You are not allowed to see list of tracking");

        }
        k.enableHtml(true);
        SendUserMessageImpl.sendMessage(k);
    }
}
