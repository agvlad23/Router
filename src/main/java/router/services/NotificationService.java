package router.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;

@WebService
public interface NotificationService {

    /**
     * @param chatId Chat id to determine to what chat send message
     * @param message message
     */
    @WebMethod
    void sendNotification(String chatId,String message);

    @WebMethod
    public void sendNotification(HashMap<String, String> map);
}
