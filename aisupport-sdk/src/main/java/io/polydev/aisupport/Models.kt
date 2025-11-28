package io.polydev.aisupport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * SDK Configuration
 */
data class AISUPConfig(
    val apiKey: String,
    val apiUrl: String,
    val wsUrl: String = apiUrl,
    val userName: String = "Guest"
)

/**
 * Message model
 */
@Serializable
data class Message(
    @SerialName("_id") val id: String,
    val chat: String,
    val content: String,
    val role: MessageRole,
    val type: MessageType = MessageType.TEXT,
    val caption: String? = null,
    val createdAt: String,
    val updatedAt: String
) {
    /** Convenience property for UI */
    val sender: MessageRole get() = role
}

@Serializable
enum class MessageRole {
    @SerialName("user") USER,
    @SerialName("bot") BOT,
    @SerialName("admin") ADMIN
}

@Serializable
enum class MessageType {
    @SerialName("text") TEXT,
    @SerialName("photo") PHOTO,
    @SerialName("file") FILE,
    @SerialName("audio") AUDIO,
    @SerialName("video") VIDEO,
    @SerialName("video_note") VIDEO_NOTE,
    @SerialName("voice") VOICE
}

/**
 * Attachment model
 */
@Serializable
data class Attachment(
    val type: AttachmentType,
    val url: String,
    val name: String,
    val size: Int? = null,
    val mimeType: String? = null
)

@Serializable
enum class AttachmentType {
    @SerialName("image") IMAGE,
    @SerialName("video") VIDEO,
    @SerialName("file") FILE
}

/**
 * Chat model
 */
@Serializable
data class Chat(
    @SerialName("_id") val id: String,
    val chatId: String,
    val platform: String,
    val status: ChatStatus,
    val mode: ChatMode,
    val userName: String? = null,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
enum class ChatStatus {
    @SerialName("active") ACTIVE,
    @SerialName("closed") CLOSED,
    @SerialName("pending") PENDING
}

@Serializable
enum class ChatMode {
    @SerialName("bot") BOT,
    @SerialName("operator") OPERATOR
}

/**
 * API Responses
 */
@Serializable
data class InitResponse(
    val response: String,
    val message: String,
    val data: InitResponseData
) {
    val chatId: String get() = data.chatId
    val startMessage: String get() = data.startMessage
}

@Serializable
data class InitResponseData(
    val chatId: String,
    val startMessage: String
)

@Serializable
data class SendMessageResponse(
    val response: String,
    val message: String
)

@Serializable
data class MessagesResponse(
    val response: String,
    val message: String,
    val data: List<Message>
) {
    val messages: List<Message> get() = data
}

@Serializable
data class UploadResponse(
    val response: String,
    val message: String,
    val data: UploadResponseData
)

@Serializable
data class UploadResponseData(
    val url: String,
    val type: String,
    val originalName: String,
    val size: Long,
    val mimetype: String,
    val messageId: String
)

/**
 * Connection status
 */
enum class ConnectionStatus {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    ERROR
}

/**
 * SDK Exceptions
 */
sealed class AISUPException(message: String) : Exception(message) {
    class NotInitialized : AISUPException("Chat not initialized. Call init() first.")
    class NetworkError(cause: Throwable) : AISUPException("Network error: ${cause.message}")
    class InvalidResponse : AISUPException("Invalid response from server")
    class ServerError(message: String) : AISUPException("Server error: $message")
    class SocketError(message: String) : AISUPException("Socket error: $message")
}
