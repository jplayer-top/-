package com.oblivion.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * github : https://github.com/oblivion0001/
 * Blog : http://blog.csdn.net/qq_16666847
 * Created by oblivion on 2017/2/26.
 */

public class ContactsInfo {
    public String name;
    public String number;
    public String sortKey;
    public int id;

    public static List<ContactsInfo> list = new ArrayList<ContactsInfo>();
    public ContactsInfo(String name, String number, String sortKey, int id) {
        this.name = name;
        this.number = number;
        this.sortKey = sortKey;
        this.id = id;
    }
}
