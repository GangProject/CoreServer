package com.gang.domain.Mastery;

/**
 * Created by seungki on 2017-06-16.
 */
public enum Ferocity {
    KEYONE(0,"분노"),KEYNINE(8,"전투의 환희"),
    KEYTWO(1,"마술"),KEYTEN(9,"분쇄의 일격"),
    KEYTHREE(2,"새로운 피"),KEYELE(10,"날카로운 정신"),
    KEYFOUR(3,"약점 노출"),KEYTWEL(11,"전쟁광의 환희"),
    KEYFIVE(4,"흡열귀"),KEYTHIR(12,"전투의 열광"),
    KEYSIX(5,"타고난 재능"),KEYFOURTY(13,"포식"),
    KEYSEVEN(6,"현상금 사냥꾼"),KEYFIFTY(14,"죽음불꼿 손길"),
    KEYEIGHT(7,"양날의 검");


    private int id;
    private String ferocity;

    Ferocity(int id,String key){
        this.id = id;
        this.ferocity = ferocity;
    }
}
