package mafia.Domain;

import lombok.Data;

@Data
public class Member {
    private int no;
    private String id;
    private String pw;
    private String nick;
    private int win;
    private int loss;
    private int score;
}
