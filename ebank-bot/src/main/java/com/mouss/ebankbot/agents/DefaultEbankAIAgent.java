package com.mouss.ebankbot.agents;

import com.mouss.ebankbot.tools.WebSearchTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class DefaultEbankAIAgent implements EbankAIAgent {

    private static final String SYSTEM_PROMPT = """
            You are the e-banking assistant of Ebank.
            Answer clearly and concisely in the language of the user.
            Use the webSearch tool whenever the user asks about current information,
            news, exchange rates, or anything that may require up-to-date data.
            Never invent banking data or search results.
            """;

    private final ChatClient chatClient;

    public DefaultEbankAIAgent(AgentProvider agentProvider, ChatMemory chatMemory,
                               WebSearchTool webSearchTool) {
        this.chatClient = agentProvider.agentBuilder()
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(webSearchTool)
                .build();
    }

    @Override
    public String chat(String conversationId, String message) {
        return chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
