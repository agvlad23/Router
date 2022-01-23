package router.client;

import bot.roles.Role;
import router.client.generated.User;
import router.client.generated.UserService;
import router.client.generated.UserServiceImplService;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void getAllUsers(){

    }
    public static Role getRole(String userId){
        return  Role.User;
    }
    public static ArrayList<User> getUsers(){
        UserServiceImplService userService = new UserServiceImplService();
        UserService usServ =userService.getUserServiceImplPort();

        var user = new ArrayList<>(usServ.findAllUsers());
        System.out.println(user);
        return user;
    }
    public static ArrayList<String> getGroups(){
        return null;
    }
}
