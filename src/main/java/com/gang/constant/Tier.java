package com.gang.constant;

/**
 * Created by Junwoo on 2017-06-02.
 */
public enum Tier {
    C("챌린저","Challenger",2700),
    M("마스터","Master",2500),
    D1("다이아몬드 1","Diamond I",2450),
    D2("다이아몬드 2","Diamond II",2400),
    D3("다이아몬드 3","Diamond III",2350),
    D4("다이아몬드 4","Diamond IV",2300),
    D5("다이아몬드 5","Diamond V",2200),
    P1("플레티넘 1","Platinum I",2100),
    P2("플레티넘 2","Platinum II",2050),
    P3("플레티넘 3","Platinum III",2000),
    P4("플레티넘 4","Platinum IV",1950),
    P5("플레티넘 5","Platinum V",1850),
    G1("골드 1","Gold I",1750),
    G2("골드 2","Gold II",1700),
    G3("골드 3","Gold III",1650),
    G4("골드 4","Gold IV",1600),
    G5("골드 5","Gold V",1500),
    S1("실버 1","Silver I",1400),
    S2("실버 2","Silver II",1350),
    S3("실버 3","Silver III",1300),
    S4("실버 4","Silver IV",1250),
    S5("실버 5","Silver V",1150),
    B1("브론즈 1","Bronze I",1050),
    B2("브론즈 2","Bronze II",1000),
    B3("브론즈 3","Bronze III",950),
    B4("브론즈 4","Bronze IV",900),
    B5("브론즈 5","Bronze V",800);

    private String tierName;
    private String tierNameEng;
    private int mmr;

    Tier(String tierName,String tierNameEng,int mmr){
        this.tierName = tierName;
        this.tierNameEng = tierNameEng;
        this.mmr = mmr;
    }
    /*
     *  mmr 에 따른 tier 명 반환
     */
    public static String getTierNameByMmr(int mmr){
        for(Tier t : Tier.values()){
            if(t.getMmr()<=mmr){
                return t.getTierName();
            }
        }
        return "브론즈 5";
    }

    /*
     * mmr 에 따른 tier 영어명 반환
     */
    public static String getTierNameEngMmr(int mmr){
        for(Tier t : Tier.values()){
            if(t.getMmr()<=mmr){
                return t.getTierNameEng();
            }
        }
        return "Bronze 5";
    }


    /*
     * tier , division과 일치하는 mmr 반환.
     */
    public static int getMmrByTier(String tier,String division){
        String fullTierName = tier+" "+division;

        System.out.println(fullTierName);

        for(Tier t : Tier.values()){
            if(t.getTierNameEng().equalsIgnoreCase(fullTierName)){
                return t.getMmr();
            }
        }

        return 800;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getTierNameEng(){
        return tierNameEng;
    }

    public void setTierNameEng(String tierNameEng){
        this.tierNameEng = tierNameEng;
    }

    public int getMmr() {
        return mmr;
    }

    public void setMmr(int mmr) {
        this.mmr = mmr;
    }
}
