package mafia.Domain;

import lombok.Data;

@Data
public class Message {
    private String type;
    // GAME, START, CHAT
    private String message;
    private String to;
    private String from;
    private Integer role;
    private Integer roomNo;
}
//메세지가 구분해야할 것들
//채팅, 겜시스템


