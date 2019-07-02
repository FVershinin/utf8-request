package com.example.utf8request

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private lateinit var context: WebApplicationContext

    @Test
    fun testAnnotationRequest() {
        val mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        val mvcResult = mockMvc.get("/annotation-request/")
            .andExpect {
                status {
                    isOk
                }
                request {
                    asyncStarted()
                }
            }
            .andReturn()
        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect {
                status().isOk
            }
            .andReturn()
    }

    @Test
    fun testFunctionRequest() {
        val mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        val mvcResult = mockMvc.get("/function-request/")
            .andExpect {
                status {
                    isOk
                }
                request {
                    asyncStarted()
                }
            }
            .andReturn()
        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect {
                status().isOk
            }
            .andReturn()
    }
}