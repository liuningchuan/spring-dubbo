package com.liuning.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class URITest {

    public static void main(String[] args) throws URISyntaxException {
        URI url = new URI("https://www.baidu.com?merId=9003&name=liuning");
        String resource = url.toString();
        String query = url.getRawQuery();
        System.out.println(query);
        resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
        System.out.println(resource);

        System.out.println(System.getProperty("user.dir"));
        System.out.println(ClassLoader.getSystemClassLoader().getResource("application.properties").getPath());

        System.out.println(Arrays.toString("er||wewe||ewe".split("\\|\\|")));
    }
}
