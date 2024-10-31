package com.nbe2.infra.chatbot.config

import org.springframework.ai.chroma.ChromaApi
import org.springframework.ai.document.Document
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.ai.vectorstore.ChromaVectorStore
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.util.Assert
import java.util.ArrayList

class CustomChromaVectorStore(
    private val embeddingModel: EmbeddingModel,
    private val chromaApi: ChromaApi,
    collectionName: String? = null,
    initializeSchema: Boolean
) :
    ChromaVectorStore(embeddingModel, chromaApi, collectionName, initializeSchema) {
    private var collectionId: String? = null

    // ChromaVectorStore의 메서드에서 필터링하는 부분만 제외, 나머지 코드 똑같음
    override fun doSimilaritySearch(request: SearchRequest): List<Document?> {
        val query = request.getQuery()
        Assert.notNull(query, "Query string must not be null")
        val embedding = embeddingModel.embed(query)
        val queryRequest = ChromaApi.QueryRequest(embedding, request.topK)
        val queryResponse = chromaApi.queryCollection(this.collectionId, queryRequest)
        val embeddings = chromaApi.toEmbeddingResponseList(queryResponse)
        val responseDocuments = ArrayList<Document?>()
        val var10 = embeddings.iterator()

        while (var10.hasNext()) {
            val chromaEmbedding: ChromaApi.Embedding = var10.next() as ChromaApi.Embedding
            val distance = chromaEmbedding.distances().toFloat()
            val id = chromaEmbedding.id()
            val content = chromaEmbedding.document()
            var metadata = chromaEmbedding.metadata()
            if (metadata == null) {
                metadata = HashMap<String, Any>()
            }

            metadata["distance"] = distance
            val document = Document(id, content, metadata)
            document.embedding = chromaEmbedding.embedding()
            responseDocuments.add(document)
        }

        return responseDocuments
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        super.afterPropertiesSet()
        this.collectionId = super.getCollectionId()
    }
}
