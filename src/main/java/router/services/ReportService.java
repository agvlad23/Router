package router.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ReportService {

    /**
     * @param chatId Chat id to determine to what chat send message
     * @param message message
     */
    @WebMethod
    void sendReport(String chatId,String message);
}
