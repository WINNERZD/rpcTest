package zd.cm.portal.service.impl;

import cm.zd.rpc.pojo.TbItem;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;
import zd.cm.portal.service.RemoteItemService;
import zd.cm.portal.utils.JsonUtils;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*负责远程调用HTTP服务 给Portal门户提供数据*/
@Service
public class RemoteItemServiceImpl implements RemoteItemService {
   /*注入远程访问的Base地址*/
    @Value("${remote.host.url}")
    private String remoteUrl;
    @Override
    public List<TbItem> SelectItemAll() {
        //  1.创建java访问http服务的客户端对象
        //  2.创建一个请求对象（get/ppost）
        //  3.发出请求，并且获取相应对象
        //  4.处理相应对象
        // 1
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2
        /*romoteURl后面可以添加参数*/
        HttpGet httpGet = new HttpGet(remoteUrl);
        // 3
        try {
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            int code = execute.getStatusLine().getStatusCode();
            if(code==200){
                HttpEntity httpEntity = execute.getEntity();
                String s = EntityUtils.toString(httpEntity);
                List<TbItem> objects = JsonUtils.jsonToList(s, TbItem.class);
                return objects;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4

        return null;
    }
    @Override
    public TbItem saveItem(TbItem tbItem){
        try {
            CloseableHttpClient aDefault = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080/item");
            ArrayList<NameValuePair> objects = new ArrayList<>();
            objects.add(new BasicNameValuePair("id","123456"));
            objects.add(new BasicNameValuePair("title","大白"));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(objects);
            httpPost.setEntity(urlEncodedFormEntity);
            CloseableHttpResponse execute = aDefault.execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            if(statusCode==200){
                HttpEntity entity = execute.getEntity();
                String s = EntityUtils.toString(entity);
                TbItem tbItem1 = JsonUtils.jsonToPojo(s, TbItem.class);
                return tbItem1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
