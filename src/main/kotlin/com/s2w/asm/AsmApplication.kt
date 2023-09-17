package com.s2w.asm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsmApplication

fun main(args: Array<String>) {
	runApplication<AsmApplication>(*args)
}
