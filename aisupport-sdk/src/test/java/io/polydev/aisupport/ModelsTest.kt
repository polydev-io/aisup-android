package io.polydev.aisupport

import org.junit.Assert.*
import org.junit.Test

class ModelsTest {

    @Test
    fun `AISUPConfig should have correct default values`() {
        val config = AISUPConfig(
            apiKey = "test-key",
            apiUrl = "https://api.test.com"
        )

        assertEquals("test-key", config.apiKey)
        assertEquals("https://api.test.com", config.apiUrl)
        assertEquals("https://api.test.com", config.wsUrl)
        assertEquals("Guest", config.userName)
    }

    @Test
    fun `AISUPConfig should accept custom wsUrl`() {
        val config = AISUPConfig(
            apiKey = "test-key",
            apiUrl = "https://api.test.com",
            wsUrl = "wss://ws.test.com",
            userName = "John"
        )

        assertEquals("wss://ws.test.com", config.wsUrl)
        assertEquals("John", config.userName)
    }

    @Test
    fun `MessageRole enum should have correct values`() {
        assertNotNull(MessageRole.USER)
        assertNotNull(MessageRole.BOT)
        assertNotNull(MessageRole.ADMIN)
    }
    
    @Test
    fun `MessageType enum should have correct values`() {
        assertNotNull(MessageType.TEXT)
        assertNotNull(MessageType.PHOTO)
        assertNotNull(MessageType.FILE)
        assertNotNull(MessageType.AUDIO)
        assertNotNull(MessageType.VIDEO)
    }

    @Test
    fun `ConnectionStatus enum should have all states`() {
        assertNotNull(ConnectionStatus.DISCONNECTED)
        assertNotNull(ConnectionStatus.CONNECTING)
        assertNotNull(ConnectionStatus.CONNECTED)
        assertNotNull(ConnectionStatus.ERROR)
    }

    @Test
    fun `ChatStatus enum should have correct values`() {
        assertNotNull(ChatStatus.ACTIVE)
        assertNotNull(ChatStatus.CLOSED)
        assertNotNull(ChatStatus.PENDING)
    }

    @Test
    fun `ChatMode enum should have correct values`() {
        assertNotNull(ChatMode.BOT)
        assertNotNull(ChatMode.OPERATOR)
    }

    @Test
    fun `AttachmentType enum should have correct values`() {
        assertNotNull(AttachmentType.IMAGE)
        assertNotNull(AttachmentType.VIDEO)
        assertNotNull(AttachmentType.FILE)
    }

    @Test
    fun `AISUPException NotInitialized should have correct message`() {
        val exception = AISUPException.NotInitialized()
        assertTrue(exception.message!!.contains("not initialized"))
    }

    @Test
    fun `AISUPException ServerError should contain error message`() {
        val exception = AISUPException.ServerError("Test error")
        assertTrue(exception.message!!.contains("Test error"))
    }

    @Test
    fun `AISUPException SocketError should contain error message`() {
        val exception = AISUPException.SocketError("Socket failed")
        assertTrue(exception.message!!.contains("Socket failed"))
    }

    @Test
    fun `AISUPException InvalidResponse should have message`() {
        val exception = AISUPException.InvalidResponse()
        assertNotNull(exception.message)
    }

    @Test
    fun `AISUPException NetworkError should wrap cause`() {
        val cause = RuntimeException("Network down")
        val exception = AISUPException.NetworkError(cause)
        assertTrue(exception.message!!.contains("Network"))
    }
}
