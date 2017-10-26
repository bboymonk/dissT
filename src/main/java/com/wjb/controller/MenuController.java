package com.wjb.controller;

import com.wjb.base.BaseController;
import com.wjb.model.Menu;
import com.wjb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @ResponseBody
    @GetMapping("list")
    public String menus(Integer parentId){
        try {
            List<Menu> menus = menuService.rootMenu(parentId);
            return successOrFail(true,menus,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successOrFail(false,null,"query error");
    }


}