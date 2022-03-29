package zatec.zatec.starwars

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PeopleController @Autowired constructor(val peopleService: PeopleService) {

    @RequestMapping(value = ["swapi/people"], method = [RequestMethod.GET])
    fun getPeople(): String {
        val people = peopleService.getPeople()

        return people
        /*return if(people == null){
            ResponseEntity<ApiResult<Person>>(null, null, HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity<ApiResult<Person>>(people, null, HttpStatus.OK)
        }*/
    }

}