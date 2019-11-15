package zd.cm.httpservice.service.impl;

import org.springframework.stereotype.Service;
import zd.cm.httpservice.mapper.TbItemMapper;
import zd.cm.httpservice.pojo.TbItem;
import zd.cm.httpservice.pojo.TbItemExample;
import zd.cm.httpservice.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem findItemById(int id) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey((long) id);
        return tbItem;
    }

    @Override
    public List<TbItem> findAll() {
        TbItemExample itemExample = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(itemExample);
        return tbItems;
    }

    @Override
    public int addItem(TbItem tbItem) {
        int insert = tbItemMapper.insert(tbItem);
    return insert;
    }
}
