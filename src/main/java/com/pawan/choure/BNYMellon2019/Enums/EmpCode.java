package com.pawan.choure.BNYMellon2019.Enums;

public enum EmpCode {
    ASSOCIATE(10001),SENIOR_ASSOCIATE(10002),MANAGER(10003),SENIOR_MANAGER(10004);

    private int code;

    EmpCode(int code) {
        this.code=code;
    }

    // also implement this method properly
    public static EmpCode getEnum(int code) {
        String name=null;
        EmpCode[] values=EmpCode.values();
        for(EmpCode e : values){
            if(e.code==code){
                name=e.name();
            }
        }
        if(name==null){
            throw  new RuntimeException("Invalid EmpCode "+code);
        }
        return EmpCode.valueOf(name);
    }
}
