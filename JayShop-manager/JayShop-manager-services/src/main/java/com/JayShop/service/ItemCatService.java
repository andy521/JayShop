package com.JayShop.service;

import com.JayShop.pojo.TbItemCat;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */
public interface ItemCatService {
    List<TbItemCat> getItemCatList(Long parentId);
}
