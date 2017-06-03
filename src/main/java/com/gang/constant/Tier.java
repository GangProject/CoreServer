package com.gang.constant;

/**
 * Created by Junwoo on 2017-06-02.
 */
public enum Tier {
    C("챌린저","Challenger",2700),
    M("마스터","Master",2500),
    D1("다이아몬드1","DiamondI",2450),
    D2("다이아몬드2","DiamondII",2400),
    D3("다이아몬드3","DiamondIII",2350),
    D4("다이아몬드4","DiamondIV",2300),
    D5("다이아몬드5","DiamondV",2200),
    P1("플레티넘1","PlatinumI",2100),
    P2("플레티넘2","PlatinumII",2050),
    P3("플레티넘3","PlatinumIII",2000),
    P4("플레티넘4","PlatinumIV",1950),
    P5("플레티넘5","PlatinumV",1850),
    G1("골드1","GoldI",1750),
    G2("골드2","GoldII",1700),
    G3("골드3","GoldIII",1650),
    G4("골드4","GoldIV",1600),
    G5("골드5","GoldV",1500),
    S1("실버1","SilverI",1400),
    S2("실버2","SilverII",1350),
    S3("실버3","SilverIII",1300),
    S4("실버4","SilverIV",1250),
    S5("실버5","SilverV",1150),
    B1("브론즈1","BronzeI",1050),
    B2("브론즈2","BronzeII",1000),
    B3("브론즈3","BronzeIII",950),
    B4("브론즈4","BronzeIV",900),
    B5("브론즈5","BronzeV",800);

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
        return "브론즈5";
    }

    /*
     * tier , division과 일치하는 mmr 반환.
     */
    public static int getMmrByTier(String tier,String division){
        String fullTierName = tier+division;

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
