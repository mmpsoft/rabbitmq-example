在 Dockerfile 目录下执行命令

1、先构建镜像

// 是以下命令构建镜像，镜像名指定为`rabbitmq-cluster:3.8.1`，需要和`docker-compose.yml`的`image`一致

```bash
docker build -t rabbitmq-cluster:3.8.1 .
```
2、执行`docker-compose`命令

```bash
docker-compose up -d
```

3、查看启动情况

```bash
docker-compose ps
```