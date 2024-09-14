# Smart Bot

## Description

Project for chatbot management based on Langchain4j library and Spring Boot framework.

Functionalities:
* secured communication with a chatbot via REST API, GraphQL & ActiveMQ broker
* persisting chat history per given user
* ability to configure chat behaviour by chatbot administrator
* ability to load additional data per user via configured URLs

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
    * one can use *docker-dev/ollama/docker-compose.yml* file to run required PostgreSQL database, ActiveMQ broker and locally hosted llama3 LLM.

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
    * one can use *docker-dev/chatgpt/docker-compose.yml* file to run required PostgreSQL database and ActiveMQ broker.
    * one has to provide own Open AI API key exposed as *OPEN_AI_API_KEY* environmental variable.

## Implementation

## Usage