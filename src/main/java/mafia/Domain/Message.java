package mafia.Domain;

public class Message {
    private MessageType type;
    private String message;
    private String to;
    private String from;
    private Integer brokerNo;
}
//메세지가 구분해야할 것들
//채팅, 겜시스템

enum MessageType{
    CHAT,
    GAME

}


