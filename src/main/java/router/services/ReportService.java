package router.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService
public interface ReportService {

    /**
     * @param chatId Chat id to determine to what chat send message
     * @param message message
     */
    @WebMethod
    void sendReport(String chatId,String message);

    @WebMethod
    void sendReport(HashMap<String,String> map);
}
