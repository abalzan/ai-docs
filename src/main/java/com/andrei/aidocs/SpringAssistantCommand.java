package com.andrei.aidocs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.command.annotation.Command;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command
public class SpringAssistantCommand {

    private final ChatModel chatModel;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/reference-docs-prompt.st")
    private Resource refPromptTemplate;

    public SpringAssistantCommand(ChatModel chatModel, VectorStore vectorStore) {
        this.chatModel = chatModel;
        this.vectorStore = vectorStore;
    }

    @Command(command = "q")
    public String question(@DefaultValue("What is Spring Boot") String message) {

        PromptTemplate prompt = new PromptTemplate(refPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));


        return chatModel
                .call(prompt.create(promptParameters))
                .getResult()
                .getOutput()
                .getContent();
    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        return documents.stream().map(Document::getContent).toList();
    }
}
