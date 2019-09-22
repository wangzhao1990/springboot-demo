FROM openjdk:v8
MAINTAINER by wangzhao
#添加包并解压
ADD output_scm/demo-api.tar.gz /data1/www/paf_release/
WORKDIR /data1/www/paf_release/demo-api/bin
ENTRYPOINT bash app_control.bash fstart
EXPOSE 8080