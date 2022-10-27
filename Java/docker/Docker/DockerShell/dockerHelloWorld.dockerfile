FROM alpinelinux/docker-cli:latest
RUN mkdir /myvol
RUN echo "hello world" > /myvol/greeting
VOLUME /myvol

#
# 构建镜像 docker build --tag dockerhelloworld:latest --file dockerHelloWorld.dockerfile .
# 运行镜像 docker run -it dockerhelloworld:latest
# 验证 cat /myvol/greeting