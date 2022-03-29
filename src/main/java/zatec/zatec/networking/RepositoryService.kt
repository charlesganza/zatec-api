package zatec.zatec.networking

import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Service
class RepositoryService {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://localhost/") //dummy URL since we don't need a base URL in our case
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun getService(): Repository {
        return retrofit.create(Repository::class.java)
    }

}