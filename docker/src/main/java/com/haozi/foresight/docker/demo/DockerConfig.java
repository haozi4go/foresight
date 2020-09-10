package com.haozi.foresight.docker.demo;

/**
 * @author liuenhao
 * @date 2020/9/10 3:44 下午
 */
public class DockerConfig {
    public static String DOCKER_HOST = "tcp://192.168.50.129:2376";
    public static String DOCKER_CERT_PATH = "/Users/liuenhao/hans/workspace/config_workspace/docker-ca";
    public static String REGISTRY_USER_NAME  = "dockeruser";
    public static String REGISTRY_PASSWORD  = "ilovedocker";
    public static String REGISTRY_EMAIL = "dockeruser@github.com";

    public static final String DOCKER_CONTAINER_WORK_DIR = "/usr/src/myapp";
}
