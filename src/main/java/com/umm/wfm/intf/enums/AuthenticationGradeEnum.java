package com.umm.wfm.intf.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * The enum Authentication grade enum.
 *
 * company umm.com  Created by zhangff on 2017/7/6.
 */
public enum AuthenticationGradeEnum {

    /**
     * Default authentication grade enum.
     */
    DEFAULT(0, "未认证"),
    /**
     * Low level authentication grade enum.
     */
    LOW_LEVEL(1, "初级认证"),
    /**
     * Middle level authentication grade enum.
     */
    MIDDLE_LEVEL(2, "中级认证");

    /**
     * @param grade 等级
     * @param name  等级名称
     */
    AuthenticationGradeEnum(Integer grade, String name) {
        this.grade = grade;
        this.name = name;
    }

    @Setter
    @Getter
    private Integer grade;
    @Setter
    @Getter
    private String name;

    /**
     * 根据grade获取对应的注释
     *
     * @param grade the grade
     * @return String name
     */
    public static String getName(int grade) {
        for (AuthenticationGradeEnum gradeEnum : AuthenticationGradeEnum.values()) {
            if (gradeEnum.getGrade().intValue() == grade) {
                return gradeEnum.getName();
            }
        }
        return null;
    }

}
