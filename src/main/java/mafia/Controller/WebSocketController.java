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
    int totUserNo = 0;
    int totRoomNo = 0;
    Set<String> waiting = new HashSet<>();
    Map<Integer,List<String>> rooms = new ConcurrentHashMap<Integer, List<String>>();
    @RequestMapping("/updateUserNo")
    @ResponseBody
    public int updateUserNo(){
        return totUserNo;
    }

//    @MessageMapping("/chat/regist/{nick}")
//    @SendTo("/topic/public/{brokerName}")
//    public Message sendMessage(@DestinationVariable(value="brokerName") String brokerName, @Payload Message message) {
//        if(chatMessage.getType()==MessageType.JOIN){
//            BrokerDTO brokerDTO = findRoom(chatMessage.getSender());
//            if(brokerDTO.getMemberName()==null){ //방을 만든 사람이라면
//                chatMessage.setBrokerName(brokerDTO.getBrokerName()); // 방이름을 넣어줌
//            }else{  //이미 있는 방에 들어간 사람이라면
//                chatMessage.setBrokerName(brokerDTO.getBrokerName()); // 방이름과
//                chatMessage.setReceiver(brokerDTO.getMemberName());   // 방에 원래있는사람의 이름을 넣어줌
//                messageTemplate.convertAndSend("/topic/public/"+brokerDTO.getMemberName(), chatMessage);
//            }
//        }
//        return chatMessage;
//    }

    @MessageMapping("/chat/regist/{nick}")
    public void regist(@DestinationVariable(value="nick") String nick){
        waiting.add(nick);
        System.out.println(nick);
        if(waiting.size()==1){
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
        for(int i = 0; i < 4 ; i ++ ){
            List members = rooms.get(totRoomNo);

            String val = (String)members.get(i);
        }
        messageTemplate.convertAndSend("/topic/public/"+brokerDTO.getMemberName(), chatMessage);

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
