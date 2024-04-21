package mafia.Domain;

import lombok.Data;

@Data
public class Member {
    private int no;
    private String id;
    private String pw;
    private String nick;
    private int win;
    //lose인데 실수로 loss로 다 해버려서 그냥 loss로 가자.
    private int loss;
    private int score;
}
