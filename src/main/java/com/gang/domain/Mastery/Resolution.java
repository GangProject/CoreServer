package com.gang.domain.Mastery;

/**
 * Created by seungki on 2017-06-16.
 */
public enum Resolution {
    KEYONE(0,"회복력"),KEYNINE(8,"인내심"),
    KEYTWO(1,"꿋꿋함"),KEYTEN(9,"대담함"),
    KEYTHREE(2,"살펴보기"),KEYELE(10,"신속"),
    KEYFOUR(3,"질긴 피부"),KEYTWEL(11,"전설의 수호자"),
    KEYFIVE(4,"수성의 대가"),KEYTHIR(12,"착취의 손아귀"),
    KEYSIX(5,"룬 갑옷"),KEYFOURTY(13,"파괴전차의 용기"),
    KEYSEVEN(6,"고참병의 흉터"),KEYFIFTY(14,"굳은 약속"),
    KEYEIGHT(7,"통찰력");


    private int id;
    private String rn;

    Resolution(int id,String rn){
        this.id = id;
        this.rn = rn;
    }

    public String getRn(){
        return rn;
    }
}
