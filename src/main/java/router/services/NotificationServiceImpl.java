package router.services;

import javax.jws.WebService;

@WebService(endpointInterface = "router.services.NotificationService")
public class NotificationServiceImpl implements NotificationService{


    /**
     * @param chatId  Chat id to determine to what chat send message
     * @param message message
     */
    @Override
    public void sendNotification(String chatId, String message) {
        System.out.println("sendNotif");
    }
}
