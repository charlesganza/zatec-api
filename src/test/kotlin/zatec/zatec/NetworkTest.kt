package zatec.zatec

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import zatec.zatec.networking.Endpoints
import zatec.zatec.networking.Repository
import zatec.zatec.starwars.ApiResult
import zatec.zatec.starwars.Person
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit

/*
* class for testing outgoing network requests
* */
class NetworkTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiInterface: Repository
    private lateinit var retrofit: Retrofit

    @Before
    fun setup(){
        val client = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("/")
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(Repository::class.java)

        val dispatcher: Dispatcher = object: Dispatcher(){
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                when(request.path){
                    Endpoints.ENDPOINT_CHUCK_NORRIS -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(loadResponse("categories_response"))
                    }
                    Endpoints.ENDPOINT_PEOPLE -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(loadResponse("people_response.json"))
                    }
                }
                return MockResponse().setResponseCode(404)
            }
        }
        mockWebServer.dispatcher = dispatcher
    }

    @Test
    fun `test get categories`(){
        runBlocking {
            val request = apiInterface.getCategories()

            request.enqueue(object: Callback<List<String>>{
                override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                    assertTrue(response.body()?.size == 16)
                }
                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    throw IllegalStateException("not supposed to happen") //intentional fail this
                }
            })
        }
    }

    @Test
    fun `test get people`(){
        runBlocking {
            val request = apiInterface.getPeople()

            request.enqueue(object: Callback<ApiResult<Person>>{
                override fun onResponse(call: Call<ApiResult<Person>>, response: Response<ApiResult<Person>>) {
                    assertTrue(response.body()?.results?.size == 4)
                    assertTrue(response.body()?.results!![0].name == "Luke Skywalker")
                }
                override fun onFailure(call: Call<ApiResult<Person>>, t: Throwable) {
                    throw IllegalStateException("not supposed to happen") //intentional fail this
                }
            })
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    private fun loadResponse(_path: String): String {
        val path = javaClass.classLoader?.getResourceAsStream("api-response-mocks/$_path.json").toString()
        return File(path).readText(Charsets.UTF_8)
    }

}

