package bot.roles;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public enum Role {
    Admin{
        @Override
        public InlineKeyboardMarkup getMarkup(){
            System.out.println("admin");
            return null;
        }
    },
    User{
        @Override
        public InlineKeyboardMarkup getMarkup(){
            System.out.println("user");
            return null;
        }

    },
    Lead{
        @Override
        public InlineKeyboardMarkup getMarkup(){
            System.out.println("lead");
            return null;
        }

    };
    public InlineKeyboardMarkup getMarkup() {

    return null;
    }
}
