package com.pawan.choure.BNYMellon2019.Enums;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(EmpCode.getEnum(10001));
        EmpCode e=EmpCode.getEnum(100088);
        System.out.println(e.name());
        System.out.println(e.toString());
    }

}
