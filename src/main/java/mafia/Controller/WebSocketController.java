package mafia.Controller;

import mafia.Domain.Message;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Controller
public class WebSocketController {
    int totUserNo = 0;
    @RequestMapping("/updateUserNo")
    @ResponseBody
    public int updateUserNo(){
        return totUserNo;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        totUserNo++;
        Message tmp = new Message();
//        tmp.setTotUser(totUserNo);
//        messageTemplate.convertAndSend("/app/chat.refresh", tmp);
//        MessageHeaders headers = event.getMessage().getHeaders();
//        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

    }
}
