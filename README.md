# VK Quote Bot

VK Quote Bot - это простой бот для ВКонтакте, который цитирует присланные ему сообщения.

## Требования

- Java 17
- Docker
- Учетная запись в Docker Hub

## Конфигурация

### Переменные окружения

Создайте файл `.env` в корневом каталоге проекта и добавьте в него следующие переменные окружения:

```plaintext
VK_API_VERSION=5.199
VK_API_URL=https://api.vk.com/method
VK_BOT_TOKEN=your_bot_token_here
VK_CONFIRMATION_CODE=your_confirmation_code_here
