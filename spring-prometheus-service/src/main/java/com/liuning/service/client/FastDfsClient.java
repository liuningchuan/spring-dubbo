package com.liuning.service.client;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
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
    public void init() throws IOException, MyException {
        String CONF_FILENAME = new ClassPathResource("fdfs_client.properties").getFile().getAbsolutePath();
        ClientGlobal.init(CONF_FILENAME);
        log.info("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        log.info("charset=" + ClientGlobal.g_charset);
    }

    /**
     * 上传文件
     *
     * @param file        文件字节流
     * @param fileExtName 文件扩展名
     * @return 文件路径：group/filePath
     * @throws Exception IOException
     */
    public String uploadFile(byte[] file, String fileExtName) throws Exception {
        StorageClient1 client = getStorageClient();
        NameValuePair[] meta_list = new NameValuePair[1];
        return client.upload_file1(file, fileExtName, meta_list);
    }

    private StorageClient1 getStorageClient() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        StorageServer storageServer = null;
        return new StorageClient1(trackerServer, storageServer);
    }

}
