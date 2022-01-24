package router.client;

import bot.roles.Role;
import router.client.generated.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static List<User> getAllUsers(){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();

        var user = new ArrayList<>(usServ.findAllUsers());
        System.out.println(user);
        return user;

    }
    public static Role getRole(String userId){
        return  Role.USER;
    }

    public static User getUser(String telegramUserId){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();

        var user = usServ.getUserByUserTelegramId(telegramUserId);
        System.out.println(user);
        return user;

    }

    public static ArrayList<User> getUsers(){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();

        var user = new ArrayList<>(usServ.findAllUsers());
        System.out.println(user);
        return user;
    }
    public static List<UserGroup> getGroups(){
        UserGroupServiceImplService grService = new UserGroupServiceImplService();
        UserGroupService grServ =grService.getUserGroupServiceImplPort();

        var groups = grServ.findAllUserGroup();
        System.out.println(groups);
        return groups;
       /* return groups;*/
    }

    public static void addUser(User user){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();
        usServ.addUser(user);

    }

    public static void deleteUser(String telegramUserId){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();
        usServ.deleteUserByTelegramId(telegramUserId);

    }
    public static void deleteUser(List<String> telegramUserIds){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();
        for (var s:telegramUserIds
             ) {
            usServ.deleteUserByTelegramId(s);
        }
    }
    public static void setRole(List<String> telegramUserIds,RoleEnum role){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();
        for (var s:telegramUserIds
        ) {
            var user=getUser(s);
            user.setUserRole(role);
            usServ.updateUser(user);
        }
    }
    public static void setGroup(List<String> telegramUserIds,String group){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();
        UserGroupServiceImplService grService = new UserGroupServiceImplService();
        UserGroupService grServ =grService.getUserGroupServiceImplPort();
        for (var s:telegramUserIds
        ) {
            var user=getUser(s);
            var userGroup=grServ.getUserGroupByName(group);
            user.setUserGroup(userGroup);;
            usServ.updateUser(user);
        }

    }
    public static void deleteGroup(List<String> userGroups){
        UserGroupServiceImplService grService = new UserGroupServiceImplService();
        UserGroupService grServ =grService.getUserGroupServiceImplPort();
        for (var s:userGroups
        ) {
            grServ.deleteUserGroupByName(s);
        }

    }
    public static void addGroup(String name,String color){
        UserGroupServiceImplService grService = new UserGroupServiceImplService();
        UserGroupService grServ =grService.getUserGroupServiceImplPort();

        boolean isNull = false;
        var group=grServ.getUserGroupByName(name);
        if (group==null) {
            group = new UserGroup();
            isNull=true;
        }
        group.setGroupName(name);
        group.setColorMark(color);

        if (isNull)
            grServ.addUserGroup(group);
        else grServ.updateUserGroup(group);
    }
}
