package bot.commands;

import bot.model.Person;
import router.client.Client;
import router.client.generated.RoleEnum;
import router.client.generated.User;

public class StartCommand implements Command {
    @Override
    public void req(Person p) {
        if (p.getUser()==null){
            User user=new User();
            var update=p.getUpdate();

            String name="";
            if (update.getMessage().getChat().getFirstName()!=null)
                name+=update.getMessage().getChat().getFirstName();
            if (update.getMessage().getChat().getLastName()!=null)
                name+=" "+update.getMessage().getChat().getLastName();
            user.setTelegramUserId(update.getMessage().getChat().getUserName());
            user.setUsername(name);
            user.setTelegramChatId(update.getMessage().getChatId().toString());
            user.setUserRole(RoleEnum.USER);
            p.setUser(user);
            Client.addUser(user);
        }
        new HelpCommand().req(p);

    }
}
