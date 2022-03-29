package zatec.zatec.search

import kotlinx.coroutines.Dispatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import zatec.zatec.networking.Endpoints
import zatec.zatec.starwars.CategoryService
import zatec.zatec.starwars.PeopleService

@RestController
@RequestMapping("/api")
class SearchController @Autowired constructor(val peopleService: PeopleService, val categoryService: CategoryService) {

    @RequestMapping(value = ["search"], method = [RequestMethod.GET])
    suspend fun search(@RequestParam(required = false) params: Map<String, String>): ResponseEntity<SearchResult> {
        //check for parameters
        return if(params.isNotEmpty()){

            //make two simultaneous requests
            val searchResult: SearchResult = withContext(Dispatchers.IO) {
                val people = async {
                    peopleService.searchPeople(params["query"].toString())
                }
                val jokes = async {
                    categoryService.searchJokes(params["query"].toString())
                }
                SearchResult(PeopleResult(link = Endpoints.ENDPOINT_PEOPLE, people.await()), JokesResultResult(link = Endpoints.ENDPOINT_CHUCK_NORRIS, jokes.await()))
            }

            //user is trying to search
            ResponseEntity<SearchResult>(searchResult, null, HttpStatus.OK)
        } else {
            ResponseEntity<SearchResult>(null, null, HttpStatus.BAD_REQUEST)
        }
    }

}