# Проверяем наличие Docker и docker-compose
if ! command -v docker &> /dev/null
then
    echo "Docker не установлен"
    exit 1
fi

# Проверяем, не запущен ли уже контейнер
if docker ps | grep selenium &> /dev/null
then
    echo "Контейнер уже запущен"
    exit 0
fi

# Запускаем контейнеры
cd ..
cd src/main/java/docker/seleniumAndTests
 docker-compose up -d
