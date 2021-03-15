import com.example.newsmvvmapp.data.model.APIService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsApiServiceTest {
    private lateinit var service: APIService;
    private lateinit var webServer: MockWebServer

    @Before
    fun setUp() {
        webServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(webServer.url("")).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(APIService::class.java)
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    fun enqueueMockResponse(filename: String) {
        var streamfile = javaClass.classLoader!!.getResourceAsStream(filename)
        var source = streamfile.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        webServer.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentrequests_receivedanswer() {
        runBlocking {
            enqueueMockResponse("newsAPi.json")
            val responseBody = service.getNewsHeadLines("us", 1).body()
            val request = webServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&api_key=92667024d01a45f4965ef90275f65703")
        }
    }
}