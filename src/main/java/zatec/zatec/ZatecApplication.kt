package zatec.zatec

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ZatecApplication

fun main(args: Array<String>) {
	SpringApplication.run(ZatecApplication::class.java, *args)
}
