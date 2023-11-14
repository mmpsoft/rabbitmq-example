ARG RABBITMQ_DOCKER_TAG=3.8-management
FROM rabbitmq:${RABBITMQ_DOCKER_TAG}

COPY --chown=rabbitmq:rabbitmq --chmod=0600 conf/erlang.cookie /var/lib/rabbitmq/.erlang.cookie
COPY --chown=rabbitmq:rabbitmq conf/rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

RUN echo "mmpsoft.com" > /var/lib/rabbitmq/.erlang.cookie

# 使用以下命令构建镜像，镜像名指定为 rabbitmq-cluster:3.8.1，需要和 docker-compose.yml 的 image 一致
# docker build -t rabbitmq-cluster:3.8.1 .