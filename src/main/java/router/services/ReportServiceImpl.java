package router.services;

import javax.jws.WebService;

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
}
