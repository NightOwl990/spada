package com.lgkk.spada.model.filter;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class FilterBean implements Serializable {
    private ArrayList<InstitutionObject> object;//收住对象
    private ArrayList<PlaceProperty> property;//机构性质
    private ArrayList<Bed> bed;//机构床位
    private ArrayList<InstitutionPlace> type;//机构类型
    private ArrayList<InstitutionFeature> feature;//机构特色
    //价格数据
    private ArrayList<InstitutionPriceBean> price;//价格
    //区域数据
    private ArrayList<ProvinceBean> area;//省市区数据


    @Data
    public static class InstitutionPlace implements Serializable {
        int id;
        String name;
        boolean isSelect;
    }

    @Data
    public static class PlaceProperty implements Serializable {
        int id;
        String name;
        boolean isSelect;
    }

    @Data
    public static class Bed implements Serializable {
        int id;
        String name;
        boolean isSelect;
    }

    @Data
    public static class InstitutionObject implements Serializable {
        int id;
        String name;
        boolean isSelect;


    }

    @Data
    public static class InstitutionFeature implements Serializable {
        int id;
        String name;
        boolean isSelect;
    }
}

