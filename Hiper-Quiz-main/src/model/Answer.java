package model;

public class Answer {
    private Question question;  // reference to the Question to which the Answer belongs;
    private String text;      // string 2 - 150 characters long, supporting Markdown syntax;
    private String picture;   // (optional) - if the Answer includes picture, valid URL;
    private int score;     // integer number (could be negative too);
}
