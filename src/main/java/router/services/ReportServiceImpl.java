package router.services;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "router.services.ReportService")
public class ReportServiceImpl implements ReportService{
    /**
     * @param chatId  Chat id to determine to what chat send message
     * @param message message
     */
    @Override
    public void sendReport(String chatId, String message) {
        System.out.println("sendReport");

    }

    @Override
    public void sendReport(HashMap<String, String> map) {
        map.forEach(this::sendReport);
    }
}
