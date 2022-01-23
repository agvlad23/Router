import bot.bot.TrackerBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import router.model.Tracking;
import router.model.User;
import router.client.RestClient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) throws InterruptedException {

/*        var user=new User();
        user.setUserId("21");
        user.setName("Client from router");
        Tracking t= new Tracking();
        t.setDate(Date.valueOf("2015-03-31"));
        t.setStartTime(new Time(12525));
        t.setEndTime(new Time(221211));
        t.setMessage("message from router client");
        var tt= new ArrayList<Tracking>();
        tt.add(t);
        user.setTracking(tt);
        RestClient.addUser(user);*/
      //  var k=RestClient.getUser();
       // RestClient.addUser(user);
       Thread t= new Thread(){
            public void run(){
        try {
            Thread.sleep(2);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TrackerBot());
        } catch (TelegramApiException | InterruptedException e) {
            e.printStackTrace();
        }
            }
        };
       t.start();
/*

        LOGGER.info("Creating WEB server and publishing SOAP endpoint");
        Endpoint.publish("http://localhost:8090/teamservice/notification/",
                new NotificationServiceImpl());

        Endpoint.publish("http://localhost:8090/teamservice/report/",
                new ReportServiceImpl());
*/

    }
}