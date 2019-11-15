package zd.cm.portal.controller;

import cm.zd.rpc.pojo.TbItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import zd.cm.portal.service.RemoteItemService;

import java.util.Iterator;
import java.util.List;

@Controller
public class PortalItemController {
    Logger logger= LoggerFactory.getLogger(PortalItemController.class);
    @Autowired
    private RemoteItemService remoteItemService;

    @RequestMapping("/item")
    public String selectAll(Model model){
        List<TbItem> tbItems = remoteItemService.SelectItemAll();
        model.addAttribute("tbItems",tbItems);
        return "index";

    }
    @RequestMapping("/item2")
    public String saveItem(){
        TbItem tbItem = remoteItemService.saveItem(new TbItem());
        logger.info("添加==="+tbItem);
        return "index";
    }
}
