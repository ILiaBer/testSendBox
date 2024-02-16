#!/bin/bash
set -x

# Проверяем наличие Docker и docker-compose
if ! command -v docker &> /dev/null
then
    echo "Docker не установлен"
    exit 1
fi

# Проверяем, запущен ли контейнер
if ! docker container inspect seleniumandtests-selenium-chrome-1 &> /dev/null
then
    echo "Контейнер не запущен"
    exit 1
fi

# Останавливаем контейнер
docker container stop seleniumandtests-selenium-chrome-1
docker container stop seleniumandtests-selenium-hub-1