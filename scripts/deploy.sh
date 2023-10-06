#!/bin/bash
IS_GREEN_EXIST=$(docker ps | grep green2)
DEFAULT_CONF=" /etc/nginx/nginx.conf"
if [ -z $IS_GREEN_EXIST ];then
  echo "### BLUE => GREEN ####"
  echo ">>> green image를 pull합니다."
  docker-compose pull green2
  echo ">>> green container를 up합니다."
  docker-compose up -d green2
  while [ 1 = 1 ]; do
  echo ">>> green health check 중..."
  sleep 3
  REQUEST=$(curl http://127.0.0.1:8082)
    if [ -n "$REQUEST" ]; then
      echo ">>> 🍃 health check success !"
      break;
    fi
  done;
  sleep 3
  echo ">>> nginx를 다시 실행 합니다."
  sudo cp /etc/nginx/nginx.green.conf /etc/nginx/nginx.conf
  sudo nginx -s reload
  echo ">>> blue container를 down합니다."
  docker-compose stop blue2
# green이 실행 중이면 blue를 up합니다.
else
  echo "### GREEN => BLUE ###"
  echo ">>> blue image를 pull합니다."
  docker-compose pull blue2
  echo ">>> blue container up합니다."
  docker-compose up -d blue2
  while [ 1 = 1 ]; do
    echo ">>> blue health check 중..."
    sleep 3
    REQUEST=$(curl http://127.0.0.1:8081)
    if [ -n "$REQUEST" ]; then
      echo ">>> 🍃 health check success !"
      break;
    fi
  done;
  sleep 3
  echo ">>> nginx를 다시 실행 합니다."
  sudo cp /etc/nginx/nginx.blue.conf /etc/nginx/nginx.conf
  sudo nginx -s reload
  echo ">>> green container를 down합니다."
  docker-compose stop green2
fi
#cicd