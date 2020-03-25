package com.alibaba.thread;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 22:09
 */

public enum CountyEnum {
    ONE(1,"齐"),TWO(2,"楚"),TRHEE(3,""),FOUR(4,""),FIVE(5,""),SIX(6,"");
    private Integer retCode;
    private String retMessage;

    CountyEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    public static CountyEnum foreach_CountryEnum(int index){
        CountyEnum[] countyEnum = CountyEnum.values();
        for (CountyEnum ele: countyEnum) {
            if(index == ele.getRetCode()){
                return ele;
            }
        }
        return null;
    }


    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
}
