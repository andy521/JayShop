package com.JayShop.controller;

import com.JayShop.common.pojo.ListProductResult;
import com.JayShop.common.utils.TaotaoResult;
import com.JayShop.pojo.TbItem;
import com.JayShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/16.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value="/item/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId")long id){
        TbItem tbItem = itemService.getTbitemById(id);
        return tbItem;
    }

    @RequestMapping(value = "/item/list")
    @ResponseBody
    public ListProductResult getItemList(int page,int rows){
        ListProductResult result = itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value = "/item/save",method =  RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem tbItem){
        return itemService.createItem(tbItem);
    }
}
