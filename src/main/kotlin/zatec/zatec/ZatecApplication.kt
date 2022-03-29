package zatec.zatec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZatecApplication

fun main(args: Array<String>) {
	runApplication<ZatecApplication>(*args)
}
