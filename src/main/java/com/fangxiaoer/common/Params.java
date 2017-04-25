package com.fangxiaoer.common;

import java.util.HashMap;

/**
 * Created by Lyn on 2017/3/15.
 */
public class Params {

    private HashMap<String, Object> self;

    public Params() {
        self = new HashMap<>();
    }
    public Params(String key, Object value) {
        self = new HashMap<>();
        add(key, value);
    }

    public Params add(String key, Object value) {
        self.put(key, value);
        return this;
    }

    public HashMap<String, Object> get() {
        return self;
    }
}
