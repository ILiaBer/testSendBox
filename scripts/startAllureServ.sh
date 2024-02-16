# Проверяем наличие Docker и docker-compose
if ! command -v docker &> /dev/null
then
    echo "Docker не установлен"
    exit 1
fi

# Проверяем, не запущен ли уже контейнер
if docker ps | grep allure &> /dev/null
then
    echo "Контейнер уже запущен"
    exit 0
fi

# Запускаем контейнеры
cd ..
cd src/main/java/docker/allure
docker-compose up -d allure

# Проверяем статус контейнеров
if docker ps | grep allure &> /dev/null
then
    echo "Контейнеры успешно запущены"
else
    echo "Ошибка запуска контейнеров"
    exit 1
fi