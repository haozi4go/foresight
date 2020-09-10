package com.haozi.foresight.docker.demo;

/**
 * @author liuenhao
 * @date 2020/9/10 10:52 上午
 */
public class Test {
    private static String code = "class Untitled {\n" +
            "\tpublic static void main(String[] args) {\n" +
            "\t\tSystem.out.println(\"hello https://tool.lu/\");\n" +
            "\t}\n" +
            "}";

    public static void main(String[] args) {
        DockerJavaClient client = new DockerJavaClient();
        client.exec(CodeLang.JAVA, code, null);

    }
}
