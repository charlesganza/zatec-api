package zatec.zatec.chuck

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import zatec.zatec.starwars.CategoryService

@RestController
@RequestMapping("/api")
class CategoryController @Autowired constructor(val categoryService: CategoryService) {

    @RequestMapping(value = ["chuck/categories"], method = [RequestMethod.GET])
    fun getCategories(): ResponseEntity<List<String>> {
        val categories = categoryService.getCategories()

        return if(categories == null){
            ResponseEntity<List<String>>(null, null, HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity<List<String>>(categories, null, HttpStatus.OK)
        }
    }

}