package com.liuning.service.client;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * fastdfs下载客户端
 *
 * @author liuning
 * @since 2021-06-08 23:27
 */
@Slf4j
@Component
public class FastDfsClient {

    @PostConstruct
    public void init() {
        try {
            String CONF_FILENAME = Thread.currentThread().getContextClassLoader().getResource("fastdfs_client.conf").getPath();
            ClientGlobal.init(CONF_FILENAME);
            TrackerGroup trackerGroup = ClientGlobal.g_tracker_group;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    private StorageClient getTrackerClient() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return  storageClient;
    }

}
