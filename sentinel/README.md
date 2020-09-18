# 踩坑记录
## 客户端指定dashboard地址后，dashboard中仍看不到应用
- 现象：   
1、客户端已有请求，且~/logs/csp/${project.name}-metrics.log.xxx中已有请求数据记录；
2、dashboard中看不到该应用及其数据；
3、sentinel-dashboard应用日志~/logs/csp/sentinel-record.log.xxx中有"Failed to get local host"报错，   
及提示 WARNING [SimpleHttpHeartbeatSender] Dashboard server address not configured or not available   
- 解决：   
sentinel-dashboard应用启动时 指定dashboard所在机器的地址及端口-Dcsp.sentinel.dashboard.server=192.168.9.9:8080  