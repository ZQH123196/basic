# 构建镜像并复制资源和启动时 java -jar 运行程序
# 并使用 docker inspect 来查看其详细部署信息

# 构建镜像 docker build -t docker101:latest -f .\docker101.dockerfile .
# 运行容器 docker run -it -p8080:8080 docker101 sh
# 复现 docker file docker history docker101

FROM openjdk:8-jre-slim

# author
MAINTAINER zqh


# 执行 命令行 创建目录，RUN 是用于创建时依次执行的命令，注意不要跑卡死的服务，CMD 则只是用于被外部覆盖的
RUN mkdir -p /home/docker101
# 指定路径
WORKDIR /home/docker101
# 挂载目录
VOLUME /home/docker101
# 复制jar文件到路径
COPY ./Docker101-1.0.jar /home/docker101/Docker101-1.0.jar

EXPOSE 8080

# 默认启动项 nohup 挂起 jobs fg
CMD ["java", "-jar", "Docker101-1.0.jar"]
# CMD 命令设置容器启动后默认执行的命令及其参数，但CMD设置的命令能够被docker run命令后面的命令行参数替换
# ENTRYPOINT 配置容器启动时的执行命令（不会被忽略，一定会被执行，即使运行 docker run时指定了其他命令）
# There can only be one CMD instruction in a Dockerfile. If you list more than one CMD then only the last CMD will take effect.
# CMD 只有最后一个会生效，会覆盖掉前面的
#CMD ["nohup", "java","-jar","Docker101-1.0.jar"]






