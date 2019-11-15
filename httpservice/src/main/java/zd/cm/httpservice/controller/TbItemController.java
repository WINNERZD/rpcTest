package zd.cm.httpservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zd.cm.httpservice.pojo.TbItem;
import zd.cm.httpservice.service.ItemService;

import java.util.Iterator;
import java.util.List;

@RestController
public class TbItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item")
    public List<TbItem> findAll(){
        List<TbItem> all = itemService.findAll();
        return all;
    }
    @RequestMapping(value = "/item",method = RequestMethod.POST)
    public TbItem addItem(TbItem tbItem){
        int i = itemService.addItem(tbItem);
        if(i==1){
            return tbItem;
        }
        return null;

    }
}
