package com.tonilopezmr.androidtesting.got.model.api;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Clase padre de todos los test de integración con la API.
 *
 * Se pueden añadir más metodos en función de lo que se quiera comprobar.
 */
public class MockWebServerTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        this.server = new MockWebServer();
        this.server.start();
    }

    @After
    public void tearDown() throws Exception {
        this.server.shutdown();
    }

    protected String getBaseEndpoint() {
        return server.url("/").toString();
    }

    protected void assertGetRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("GET", request.getMethod());
    }

    protected void assertPostRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("POST", request.getMethod());
    }

    protected void assertRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
    }

    protected void assertRequestBodyEquals(String jsonRequest) throws InterruptedException, IOException {
        RecordedRequest request = server.takeRequest();
        assertEquals(jsonRequest, request.getBody().readUtf8());
    }

    protected void enqueueMockResponse() throws IOException {
        enqueueMockResponse(200);
    }

    protected void enqueueMockResponse(int code) throws IOException {
        enqueueMockResponse(code);
    }

    protected void enqueueMockResponse(String body) throws IOException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(200);
        mockResponse.setBody(body);
        server.enqueue(mockResponse);
    }

    protected void assertRequestContainsHeader(String key, String expectedValue)
            throws InterruptedException {
        assertRequestContainsHeader(key, expectedValue, 0);
    }

    protected void assertRequestContainsHeader(String key, String expectedValue, int requestIndex)
            throws InterruptedException {
        RecordedRequest recordedRequest = getRecordedRequestAtIndex(requestIndex);
        String value = recordedRequest.getHeader(key);
        assertEquals(expectedValue, value);
    }

    private RecordedRequest getRecordedRequestAtIndex(int requestIndex) throws InterruptedException {
        RecordedRequest request = null;
        for (int i = 0; i <= requestIndex; i++) {
            request = server.takeRequest();
        }
        return request;
    }

}
