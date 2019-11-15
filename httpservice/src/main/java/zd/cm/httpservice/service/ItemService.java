package zd.cm.httpservice.service;

import zd.cm.httpservice.pojo.TbItem;

import java.util.List;

public interface ItemService {
    public TbItem findItemById(int id);
    public List<TbItem> findAll();

    public int addItem(TbItem tbItem);
}
