package mafia.Controller;

import jakarta.servlet.http.HttpSession;
import mafia.Domain.Member;
import mafia.Domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messageTemplate;
//    WebSocketSession session;
    int roleNo = 0;
    int totUserNo = 0;
    int totRoomNo = 0;
    Set<String> waiting = new HashSet<>();

    Map<Integer, List<String>> rooms = new ConcurrentHashMap<Integer, List<String>>();
//    Map<Integer, List<String>>
    @RequestMapping("/updateUserNo")
    @ResponseBody
    public int updateUserNo(){
        return totUserNo;
    }

    @MessageMapping("/chat/vote1/{nowRoomNo}")
    public void vote1(@DestinationVariable (value="roomNo") String nowRoomNo, @Payload Message message){
        message.setType("VOTE1");
        messageTemplate.convertAndSend("/topic/public/"+nowRoomNo, message);
    }
    @MessageMapping("/chat/sendMessage/{nowRoomNo}")
//    @SendTo("/topic/public/{nowRoomNo}")
    public void sendMessage(@DestinationVariable(value="nowRoomNo") String nowRoomNo, @Payload Message message) {
        System.out.println("sendMEssage nowRoomNo = "+nowRoomNo);
        System.out.println("message sender = "+message.getFrom());
        System.out.println("message type = "+message.getType());
        if(message.getType().equals("CHAT")){
            messageTemplate.convertAndSend("/topic/public/"+nowRoomNo, message);
        }
        return;
    }

    @MessageMapping("/chat/regist/{nick}")
    public void regist(@DestinationVariable(value="nick") String nick){
        waiting.add(nick);
        System.out.println(nick);
        if(waiting.size()==2){
            makeRoom();
        }
    }
    public void makeRoom(){
        List<String> roomMembers = new ArrayList<>();

        Iterator<String> iterator = waiting.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            roomMembers.add(value);
            System.out.println("value = "+value);
            iterator.remove(); // Iterator를 통해 요소 삭제
            System.out.println("waiting size = " + waiting.size());
        }
        rooms.put(totRoomNo, roomMembers);
        gameStart(totRoomNo);
        totRoomNo++;
    }
    public void gameStart(int roomNo){
        int tmp = roleNo;
        List<String> memList = rooms.get(roomNo);
        System.out.println("list : "+memList.toString());
        roleNo++;
        Message message = new Message();
        message.setType("START");
        message.setRoomNo(roomNo);
        message.setMembers(memList);
        for(int i = 0; i < 2 ; i ++ ){
            tmp = (tmp+i)%4;
            message.setRole(tmp);
            // 0-시민 1-의사 2-시민 3-마피아
            List members = rooms.get(totRoomNo);
            String val = (String)members.get(i);
            messageTemplate.convertAndSend("/topic/public/"+val, message);
        }
        message.setType("CHAT");
        message.setTo("");
        message.setFrom("**GAME SYSTEM**");
        message.setMessage("게임이 시작됐습니다. 30초 후 투표가 시작됩니다.");
        messageTemplate.convertAndSend("/topic/public/"+roomNo, message);
        System.out.println(message.getMessage());
        return;
    }


    @MessageMapping("/chat/test")
    public void test(){
        System.out.println("test succeed");
    }



    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        totUserNo++;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        System.out.println(username);
//        Message tmp = new Message();
//        tmp.setTotUser(totUserNo);
//        messageTemplate.convertAndSend("/app/chat.refresh", tmp);
//        MessageHeaders headers = event.getMess age().getHeaders();
//        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        totUserNo--;
//        WebSocketSession session = (WebSocketSession) event.getSource();
//        String sessionId = (String) session.getAttributes().get("sessionId");

//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headerAccessor.getSessionId();
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        // WebSocket 세션에서 "id" 변수를 꺼내와서 사용
//        String sessionId = (String) accessor.getSessionAttributes().get("sessionId");
//
//        // 세션아이디로 Map에서 현재방 찾아서 없애야함 그리고 상대방도 disconnect시켜야함.
//        removeSession(sessionId);

//        ChatMessage tmp = new ChatMessage();
//
//        tmp.setTotUser(totUserNo);
//        tmp.setType(MessageType.LEAVE);
//        messageTemplate.convertAndSend("/topic/public/"+sessionId, tmp);

    }
}
