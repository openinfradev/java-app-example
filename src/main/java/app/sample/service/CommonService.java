package app.sample.service;

import app.sample.value.Container;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public String getMyIp() throws Exception {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    public List<Container> getContainers() {
        // "{\"containers\":[{\"port\":4293,\"host\":\"196.35.49.254\"}]}"
        String str = valueOperations.get("containerList");
        JSONArray containerArr;

        JSONParser parser = new JSONParser();
        List<Container> containers = new ArrayList<>();

        try {
            JSONObject json = (JSONObject) parser.parse(str);
            containerArr = (JSONArray) json.get("containers");
            containers = (List<Container>) containerArr.stream().map(value -> {
                JSONObject object = (JSONObject) value;
                String host = String.valueOf(object.get("host"));
                Long port = (Long) object.get("port");
                return new Container(host, port.intValue());
            }).collect(Collectors.toList());

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return containers;
    }

    public void syncContainer() {
        try {
            Container container = new Container(this.getMyIp(), 8080);

            List<Container> containers = this.getContainers();

            boolean exists = false;
            for (Container item : containers) {
                if( container.getHost().equals(item.getHost()) ) {
                    exists = true;
                    break;
                }
            }
            if(!exists) {
                containers.add(container);
                JSONObject json = this.mappingToJson(containers);
                valueOperations.set("containerList", json.toJSONString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public JSONObject mappingToJson(List<Container> containers) {
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        containers.forEach(item -> {
            JSONObject o = new JSONObject();
            o.put("host", item.getHost());
            o.put("port", item.getPort());
            arr.add(o);
        });
        json.put("containers", arr);
        return json;
    }
}
