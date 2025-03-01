package com.lgkk.spada.model.community.group;

import com.lgkk.spada.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JoinGroupDetails extends GroupDetails implements Serializable {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

