
# 构建镜像 docker build -t mytomcat -f .\dockerTomcat.dockerfile .
# 运行容器 docker run -it --name mytomcat -p8080:8080 mytomcat
# 访问容器 docker exec -it mytomcat sh
# 查看进程 ps -ef | grep tomcat
# 宿主机验证 hello 接口 GET http://localhost:8080/Docker101-1.0/hello


FROM tomcat:9.0.68-jre8

# author
MAINTAINER zqh

ARG webappsDir=/usr/local/tomcat/webapps
ARG target=Docker101-1.0.war

# 把 tomcat 默认的页面给弄过来
RUN cp -r  /usr/local/tomcat/webapps.dist/* ${webappsDir}
# 切换到指定路径
WORKDIR ${webappsDir}
# 挂载目录
VOLUME ${webappsDir}
# 复制jar文件到路径
COPY ./${target} ${webappsDir}/${target}
EXPOSE 8080

