package com.JayShop.service.impl;

import com.JayShop.mapper.TbItemMapper;
import com.JayShop.pojo.TbItem;
import com.JayShop.pojo.TbItemExample;
import com.JayShop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理service
 * Created by Administrator on 2016/10/16.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem getTbitemById(long itemId) {
//        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria c = tbItemExample.createCriteria();
        c.andIdEqualTo(itemId);
        List<TbItem>  list = tbItemMapper.selectByExample(tbItemExample);
        if(list!=null&&list.size() >0){
            TbItem item = list.get(0);
            return item;
        }
        return null;

    }
}
