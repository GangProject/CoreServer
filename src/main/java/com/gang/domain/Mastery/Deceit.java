package com.gang.domain.Mastery;

/**
 * Created by seungki on 2017-06-16.
 */
public enum Deceit {
    KEYONE(0,"방랑자"),KEYNINE(8,"산적"),
    KEYTWO(1,"포악함"),KEYTEN(9,"위험한 게임"),
    KEYTHREE(2,"룬 친화력"),KEYELE(10,"바람술사의 축복"),
    KEYFOUR(3,"비밀 창고"),KEYTWEL(11,"정확성"),
    KEYFIVE(4,"암살자"),KEYTHIR(12,"지능"),
    KEYSIX(5,"무자비"),KEYFOURTY(13,"폭풍전사의 포효"),
    KEYSEVEN(6,"명상"),KEYFIFTY(14,"천둥군주의 호령"),
    KEYEIGHT(7,"대자연의 선물");


    private int id;
    private String ferocity;

    Deceit(int id,String key){
        this.id = id;
        this.ferocity = ferocity;
    }
}
