package com.lgkk.spada.model;

import java.util.List;

import lombok.Data;

@Data
public class HotSpaDetail {
    String id = "";
    List<SpaDetail> spaList;
}
