// src/main/java/com/example/springbootstg2/payload/response/MessageResponse.java
package com.example.springbootstg2.payload.response;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter (optional, but good practice for deserialization if needed elsewhere)
    public void setMessage(String message) {
        this.message = message;
    }
}