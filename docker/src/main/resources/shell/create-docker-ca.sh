#!/bin/bash

SERVER="192.168.50.129"
PASSWORD="chuangzhi8.cn"
COUNTRY="CN"
STATE="Beijing"
CITY="Beijing"
ORGANIZATION="chuangzhi8"
ORGANIZATIONAL_UNIT="Dev"
EMAIL="blog@chuangzhi8.cn"

###开始生成文件###
echo "开始生成文件"

#切换到生产密钥的目录
cd /opt/docker_ca
#生成ca私钥(使用aes256加密)
openssl genrsa -aes256 -passout pass:$PASSWORD -out ca-key.pem 4096
#生成ca证书，填写配置信息
openssl req -new -x509 -passin "pass:$PASSWORD" -days 365 -key ca-key.pem -sha256 -out ca.pem -subj "/C=$COUNTRY/ST=$STATE/L=$CITY/O=$ORGANIZATION/OU=$ORGANIZATIONAL_UNIT/CN=$SERVER/emailAddress=$EMAIL"

#生成server证书私钥文件
openssl genrsa -out server-key.pem 4096
#生成server证书请求文件
openssl req -subj "/CN=$SERVER" -sha256 -new -key server-key.pem -out server.csr
#配置白名单，多个用逗号隔开
sh -c 'echo subjectAltName = IP:'$SERVER',IP:0.0.0.0 >> extfile.cnf'
#把 extendedKeyUsage = serverAuth 键值设置到extfile.cnf文件里，限制扩展只能用在服务器认证
sh -c 'echo extendedKeyUsage = serverAuth >> extfile.cnf'
#使用CA证书及CA密钥以及上面的server证书请求文件进行签发，生成server自签证书
openssl x509 -req -days 365 -sha256 -in server.csr -CA ca.pem -CAkey ca-key.pem -passin "pass:$PASSWORD" -\CAcreateserial -out server-cert.pem -extfile extfile.cnf

#生成client证书RSA私钥文件
openssl genrsa -out key.pem 4096
#生成client证书请求文件
openssl req -subj '/CN=client' -new -key key.pem -out client.csr
#继续设置证书扩展属性
sh -c 'echo extendedKeyUsage = clientAuth >> extfile.cnf'
#生成client自签证书（根据上面的client私钥文件、client证书请求文件生成）
openssl x509 -req -days 365 -sha256 -in client.csr -CA ca.pem -CAkey ca-key.pem -passin "pass:$PASSWORD" -\CAcreateserial -out cert.pem -extfile extfile.cnf

#更改密钥权限
chmod 0400 ca-key.pem key.pem server-key.pem
#更改密钥权限
chmod 0444 ca.pem server-cert.pem cert.pem
#删除无用文件
rm client.csr server.csr

echo "生成文件完成"
###生成结束###

# 2、基于上面生成的密钥修改docker配置
## vim /lib/systemd/system/docker.service
## 添加以下配置
## ExecStart=/usr/bin/dockerd \
#        --tlsverify \
#        --tlscacert=/opt/docker_ca/ca.pem \
#        --tlscert=/opt/docker_ca/server-cert.pem \
#        --tlskey=/opt/docker_ca/server-key.pem \
#        -H tcp://0.0.0.0:2376 \
#        -H unix:///var/run/docker.sock \
#        -H fd:// --containerd=/run/containerd/containerd.sock

# 3、重启docker（放开宿主机的2376端口）
## systemctl daemon-reload && systemctl restart docker
# 4、拷贝ca.pem、 cert.pem、 key.pem作为密钥 拷贝到要访问远程访问docker到机器上
## 如idea 远程访问：https://img-blog.csdnimg.cn/20200328194902869.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0Fzc2Fzc2luX0VaSTA=,size_16,color_FFFFFF,t_70

##refer: https://blog.csdn.net/Assassin_EZI0/article/details/105167118
