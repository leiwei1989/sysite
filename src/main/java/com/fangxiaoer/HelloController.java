package com.fangxiaoer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiansai on 2017/4/17.
 */
@RestController
public class HelloController {

    @GetMapping("/show")
    public Human showHuman() {
        return new Human(1, "william", "boy", 20);
    }

    @GetMapping("/showList")
    public List<Human> showHumans() {
        List<Human> list =  new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            list.add(new Human(1, "william", "boy", 20));
        }
        return list;
    }


}
