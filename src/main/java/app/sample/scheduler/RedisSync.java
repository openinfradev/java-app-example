package app.sample.scheduler;

import app.sample.service.CommonService;
import app.sample.value.Container;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RedisSync {

    private static final Logger logger = LoggerFactory.getLogger(RedisSync.class);

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private CommonService commonService;

    // every 30 seconds
    @Scheduled(fixedDelay = 1000 * 30)
    public void checkContainers() {
        // redis 에서 builder server 목록 조회
        // "{\"builders\":[{\"port\":4293,\"host\":\"196.35.49.254\"}]}"

        List<Container> checkedContainers = new ArrayList<>();
        List<Container> containers = commonService.getContainers();
        containers.forEach(c -> {
            String url = "http://" + c.getHost() + ":" + c.getPort() + "/health";

            logger.info("check container health : {}", url);
            HttpURLConnection connection = null;
            try {
                URL siteURL = new URL(url);
                connection = (HttpURLConnection) siteURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.connect();

                int code = connection.getResponseCode();
                if (code == 200) {
                    checkedContainers.add(c);
                }
                connection.disconnect();
                logger.info("result code : {}", code);
            } catch (Exception e) {
                if (connection != null) {
                    connection.disconnect();
                }
                logger.error("{} {} : {}", e.getMessage(), c.getHost(), c.getPort());
            }
        });

        JSONObject json = commonService.mappingToJson(checkedContainers);
        valueOperations.set("containerList", json.toJSONString());
    }
}
