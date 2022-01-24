package bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import bot.roles.Role;
import router.client.generated.User;


@AllArgsConstructor
public class Person {
    @Setter
    @Getter
    User user;
    /*@Getter
    Role role=Role.User;*/
    @Getter
    Update update;

    @Getter
    @Setter
    Role role;

}
