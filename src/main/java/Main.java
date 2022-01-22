import bot.bot.TrackerBot;
import bot.model.User;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import router.client.RestClient;
import router.services.NotificationServiceImpl;
import router.services.ReportServiceImpl;

import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) throws InterruptedException {

        var user=new User();
        var k=RestClient.getUser();
        RestClient.addUser(user);
    /*   Thread t= new Thread(){
            public void run(){
        try {
            Thread.sleep(2222);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TrackerBot());
        } catch (TelegramApiException | InterruptedException e) {
            e.printStackTrace();
        }
            }
        };
       t.start();

        LOGGER.info("Creating WEB server and publishing SOAP endpoint");
        Endpoint.publish("http://localhost:8090/teamservice/notification/",
                new NotificationServiceImpl());

        Endpoint.publish("http://localhost:8090/teamservice/report/",
                new ReportServiceImpl());*/

    }
}