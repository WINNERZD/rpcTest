package zd.cm.portal.service;

import cm.zd.rpc.pojo.TbItem;

import java.util.List;

public interface RemoteItemService {

    public List<TbItem> SelectItemAll();

    public  TbItem saveItem(TbItem tbItem);
}
