package com.JayShop.service;

import com.JayShop.common.pojo.ListProductResult;
import com.JayShop.pojo.TbItem;

/**
 * Created by Administrator on 2016/10/16.
 */
public interface ItemService {
    TbItem getTbitemById(long itemId);
    ListProductResult<TbItem> getItemList(int page,int rows);
}
