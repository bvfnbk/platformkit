package com.github.bvfnbk.oskit

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import com.github.bvfnbk.oskit.error.RequiredSystemPropertyMissingError
import com.github.bvfnbk.oskit.error.UnknownOperatingSystemError
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.*

internal class OperatingSystemTest {
    var originalProperties: Properties? = null
    var testProperties: Properties? = null

    @BeforeEach
    internal fun setUp() {
        originalProperties = System.getProperties()
        testProperties = Properties()
        System.setProperties(testProperties)
    }

    @AfterEach
    internal fun tearDown() {
        System.setProperties(originalProperties)
        testProperties = null
    }

    @TestFactory
    fun `Known operating system names return expected enum constants`() =
        listOf(
            "Linux" to OperatingSystem.LINUX,
            "Mac OS X" to OperatingSystem.MAC_OS_X,
            "Windows 11" to OperatingSystem.WINDOWS
        ).map { (name, expected) ->
            dynamicTest(
                "Given os.name \"$name\" " +
                    "When retrieving the operating system " +
                    "Then it should return OperatingSystem.$expected"
            ) {
                testProperties!!["os.name"] = name
                val actual = OperatingSystem.get()
                assertThat(actual).isEqualTo(expected)
            }
        }

    @Test
    fun `A missing system property throws an RequiredSystemPropertyMissingError`() {
        assertThat { OperatingSystem.get() }
            .isFailure()
            .isInstanceOf(RequiredSystemPropertyMissingError::class)
            .prop(RequiredSystemPropertyMissingError::key)
            .isEqualTo("os.name")
    }

    @Test
    fun `An unknown operating system name triggers an UnknownOperatingSystemError`() {
        val invalidOsName = "i am no known operating system name"
        assertThat {
            testProperties!!["os.name"] = invalidOsName
            OperatingSystem.get()
        }.isFailure()
            .isInstanceOf(UnknownOperatingSystemError::class)
            .prop(UnknownOperatingSystemError::name)
            .isEqualTo(invalidOsName)
    }
}
