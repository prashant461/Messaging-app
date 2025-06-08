# 📩 Messaging Microservice - Snoodify

This project is a **Spring Boot-based messaging microservice** that supports both **normal** and **anonymous** chat functionalities, real-time notifications using **Kafka**, and features like **chat history**, **reactions**, **message search**, **chat muting**, and more.

---

## 🚀 Features

- 📥 Send and receive **normal messages**
- 🕵️ Send and receive **anonymous messages**
- 🧵 Retrieve **chat history** with pagination
- ❤️ Add or remove **reactions** to messages
- 🔍 **Search** messages in a chat
- 🔕 **Mute** or **delete** chats
- 📦 Kafka producer-consumer integration for real-time updates
- 🖼️ Lazy loading for **profile pictures** via REST call

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Kafka**
- **ModelMapper**
- **RestTemplate**
- **MySQL/PostgreSQL** (any relational DB)
- **Lombok**

---

## 📂 Project Structure

```

src
├── controller
│   ├── ChatController.java
│   └── MessageController.java
├── service
│   ├── impl
│   └── interfaces
├── model
├── repository
├── kafka
│   ├── MessageProducer.java
│   └── MessageConsumer.java
└── dto

````

---

## 🔧 Getting Started

### Prerequisites

- Java 17+
- Maven
- Kafka running locally or on cloud (like AWS MSK)
- MySQL/PostgreSQL database

### Clone and Setup

```bash
git clone https://github.com/your-username/snoodify-messaging-service.git
cd snoodify-messaging-service
````

### Configure DB & Kafka

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chatdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.kafka.bootstrap-servers=localhost:9092
```

### Run Kafka (if local)

```bash
# Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka broker
bin/kafka-server-start.sh config/server.properties

# Create required topics
bin/kafka-topics.sh --create --topic message --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic anonymous --bootstrap-server localhost:9092
```

### Run the App

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🧪 REST API Endpoints

### 🔹 ChatController

| Method   | Endpoint                                | Description              |
| -------- | --------------------------------------- | ------------------------ |
| `GET`    | `/v1/chat/allChats/{senderId}`          | Get all chats for a user |
| `POST`   | `/v1/chat/new/{senderId}/{recipientId}` | Start a new chat         |
| `PATCH`  | `/v1/chat/mute/{chatId}/{user}`         | Mute a chat              |
| `DELETE` | `/v1/chat/delete/{chatId}/{user}`       | Soft delete a chat       |

---

### 🔹 MessageController

| Method   | Endpoint                                             | Description                     |
| -------- | ---------------------------------------------------- | ------------------------------- |
| `POST`   | `/v1/messages/send`                                  | Send a message                  |
| `GET`    | `/v1/messages/history/{chatId}/{user}`               | Get paginated chat history      |
| `GET`    | `/v1/messages/search/{chatId}/{user}?text=query`     | Search messages                 |
| `POST`   | `/v1/messages/sendReaction/{messageId}/{reactionId}` | Add a reaction                  |
| `DELETE` | `/v1/messages/deleteReaction/{messageId}`            | Remove a reaction               |
| `DELETE` | `/v1/messages/delete/{chatId}/{user}`                | Soft delete messages for a user |

---

## 🧩 Kafka Integration

* **Producer**: Sends messages to topics `message` and `anonymous`
* **Consumer**: Listens to these topics and logs them (can be extended to notify users via socket or push service)

---

## 📸 Future Improvements

* WebSocket integration for real-time UI updates
* Authentication and user info service integration
* Message read receipts and typing indicators
* UI frontend with React/Angular

---

## 🤝 Contributing

1. Fork the repository
2. Create your branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a pull request
