package controller;

import com.JayShop.mapper.TbItemMapper;
import com.JayShop.pojo.TbItem;
import com.JayShop.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */

public class TestPageHelper {
    @Test
    public void TestPageHelper(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        TbItemExample tbItemExample = new TbItemExample();
        PageHelper.startPage(1,10);
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        for(TbItem tbItem: list){
            System.out.println(tbItem.getTitle());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品信息："+total);
    }
}

