FROM gradle:9-jdk25

WORKDIR /app

ARG HOST_USER
ARG HOST_USER_ID
ARG HOST_GROUP
ARG HOST_GROUP_ID

RUN deluser gradle \
    && groupadd -g ${HOST_GROUP_ID} ${HOST_GROUP} \
    && useradd -r -u ${HOST_USER_ID} -g ${HOST_GROUP} -s /bin/bash -m ${HOST_USER} \
    && chown -R ${HOST_USER}:${HOST_GROUP} /app \
    && apt-get update && apt-get install -y tzdata curl ffmpeg \
    && apt-get install -y iputils-ping \
    && ln -fs /usr/share/zoneinfo/${TIMEZONE} /etc/localtime \
    && echo "${TIMEZONE}" > /etc/timezone

EXPOSE 8080

USER ${HOST_USER}

CMD ["bash", "-c", "gradle -t compileJava & gradle bootRun"]
