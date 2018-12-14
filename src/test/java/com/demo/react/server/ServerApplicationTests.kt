package com.demo.react.server

import com.demo.react.server.runtime.JavaScriptRuntime
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ServerApplicationTests {

    @Autowired
    private lateinit var runtime: JavaScriptRuntime

    @Test
    fun contextLoads() {
    }

}
