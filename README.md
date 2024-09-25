# Smart Bot

## Description

Project for chatbot management based on Langchain4j library and Spring Boot framework.

Functionalities:
* secured communication with chatbots via REST API, GraphQL & ActiveMQ broker
* persisting chat history per given user
* ability to configure chat behaviour by chatbot administrator
* ability to statically load RAG set of data per user from preconfigured sources
* ability to dynamically load additional data per user via configured URLs
* ability to delegate LLM to send emails to a preconfigured set of receivers per user

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To properly build an application one might need to install [Java 17](https://www.oracle.com/th/java/technologies/downloads/#java17) and/or [Docker](https://www.docker.com/get-started/).

### Cloning

```
$ git clone https://github.com/ninjarlz/smartbot
```

### Building

Using the installation of Java 17 and built-in gradle wrapper:
```
$ ./gradlew build
```
Additionally, one can build & run application using provided [Dockerfile](Dockerfile).

## Configuration

Contains configuration for:
* **locally hosted LLM (llama3.1)**:
    * *'ollama'* Spring profile defined in *application-ollama.yml* file:
      ```
      langchain4j:
        ollama:
          chat-model:
            model-name: llama3.1
            temperature: 0.2
            base-url: http://localhost:11434
            format: json
            timeout: 1200s
      ```
    * one can use [ollama/docker-compose.yml](docker-dev/ollama/docker-compose.yml) file to run required PostgreSQL database, ActiveMQ broker, Vault instance and locally hosted llama3 LLM.

* **cloud-based ChatGPT 3.5**:
    * *'chatgpt'* Spring profile defined in *application-chatgpt.yml* file:
      ```
      langchain4j:
        open-ai:
          chat-model:
            api-key: ${OPEN_AI_API_KEY}
            model-name: gpt-3.5-turbo
            temperature: 0.2
            log-requests: true
            log-responses: true
            response-format: json_object
            timeout: 1200s
      ```
    * one can use [chatgpt/docker-compose.yml](docker-dev/chatgpt/docker-compose.yml) file to run the required PostgreSQL database, ActiveMQ broker and Vault instance.
    * one has to provide one's own Open AI API key exposed as *OPEN_AI_API_KEY* environmental variable.

## Implementation

## Usage

## Built with
* [Spring Boot](https://spring.io/projects/spring-boot) - The world’s leading Java web app creation platform
* [Langchain4j](https://docs.langchain4j.dev) - A Java library designed to facilitate the development of applications leveraging large language models (LLMs) and integrating natural language processing (NLP) capabilities.

## Developers
* **Michał Kuśmidrowicz** - [ninjarlz](https://github.com/ninjarlz)

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
