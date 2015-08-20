package org.grails.jaxrs.itest

import org.grails.jaxrs.test.CustomRequestEntityReader
import org.grails.jaxrs.test.CustomResponseEntityWriter
import org.grails.jaxrs.test.TestResource01
import org.grails.jaxrs.test.TestResource02
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class ExampleIntegrationTest extends IntegrationTestCase {

    List getJaxrsClasses() {
        [TestResource01,
         TestResource02,
         CustomRequestEntityReader,
         CustomResponseEntityWriter]
    }

    @Test
    void testGet() {
        sendRequest('/test/01', 'GET')
        assertEquals(200, response.status)
        assertEquals('test01', response.contentAsString)
        assertTrue(response.getHeader('Content-Type').startsWith('text/plain'))
    }

    @Test
    void testPost() {
        sendRequest('/test/02', 'POST', ['Content-Type':'text/plain'], 'hello'.bytes)
        assertEquals(200, response.status)
        assertEquals('response:hello', response.contentAsString)
        assertTrue(response.getHeader('Content-Type').startsWith('text/plain'))
    }
}
