# 踩坑记录
## 客户端指定dashboard地址后，dashboard中仍看不到应用
- 现象：   
1、客户端已有请求，且~/logs/csp/${project.name}-metrics.log.xxx中已有请求数据记录；
2、dashboard中看不到该应用及其数据；
3、sentinel-dashboard应用日志~/logs/csp/sentinel-record.log.xxx中有"Failed to get local host"报错，   
及提示 WARNING [SimpleHttpHeartbeatSender] Dashboard server address not configured or not available   
- 解决：   
sentinel-dashboard应用启动时 指定dashboard所在机器的地址及端口-Dcsp.sentinel.dashboard.server=192.168.9.9:8080  

## pom引入spring-cloud-starter-openfeign失败

- 现象：
1、maven报错：Cannot resolve org.springframework.cloud:spring-cloud-starter-openfeign:unknown
- 解决：
1、在https://start.spring.io/中指定与当前SpringBoot相同版本2.2.10.RELEASE
2、界面选择添加OpenFeign依赖，生成demo工程，并对比demo工程中的pom引用与现有工程中的pom文件的不同     
dependencyManagement中少
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>${spring-cloud.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
和       
 ```xml
<spring-cloud.version>Hoxton.SR8</spring-cloud.version>
```
3、添加以上两项，问题解决   
- 原因
1、生成sentinel工程时只引入如了sentinel相关的包，并没引入OpenFeign，导致只有spring-cloud-alibaba-dependencies，而没引spring-cloud-dependencies
恰巧OpenFeign在spring-cloud-alibaba-dependencies中没有