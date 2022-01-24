package bot.roles;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import router.client.generated.RoleEnum;

public enum Role {
    ADMIN {
        @Override
        public InlineKeyboardMarkup getMarkup() {
            System.out.println("admin");
            return null;
        }
    },
    USER {
        @Override
        public InlineKeyboardMarkup getMarkup() {
            System.out.println("user");
            return null;
        }

    },
    TEAM_LEAD {
        @Override
        public InlineKeyboardMarkup getMarkup() {
            System.out.println("lead");
            return null;
        }
    },
    TEACHER {
        @Override
        public InlineKeyboardMarkup getMarkup() {
            System.out.println("teacher");
            return null;
        }

    };

    public InlineKeyboardMarkup getMarkup() {

        return null;
    }

    public static Role toRole(RoleEnum roleEnum) {
        //Role role=Role.valueOf(roleEnum.value());
        if (roleEnum != null)
            return Role.valueOf(roleEnum.value());
        else return null;
    }
}
