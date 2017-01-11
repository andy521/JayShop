package com.JayShop.service.impl;

import com.JayShop.common.pojo.ListProductResult;
import com.JayShop.common.utils.IDUtils;
import com.JayShop.common.utils.TaotaoResult;
import com.JayShop.mapper.TbItemMapper;
import com.JayShop.pojo.TbItem;
import com.JayShop.pojo.TbItemExample;
import com.JayShop.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public ListProductResult<TbItem> getItemList(int page,int rows){
        TbItemExample tbItemExample = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        ListProductResult listProductResult = new ListProductResult();
        listProductResult.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        listProductResult.setTotal(pageInfo.getTotal());
        return listProductResult;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem) {
        long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus(true);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        int rows = tbItemMapper.insert(tbItem);
        return TaotaoResult.ok();
    }
}
