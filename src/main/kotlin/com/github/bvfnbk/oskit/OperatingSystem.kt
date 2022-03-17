package com.github.bvfnbk.oskit

import com.github.bvfnbk.oskit.error.RequiredSystemPropertyMissingError
import com.github.bvfnbk.oskit.error.UnknownOperatingSystemError

/**
 * An enumeration of all supported operating systems.
 *
 * @author bvfnbk
 */
enum class OperatingSystem {
    LINUX,
    MAC_OS_X,
    WINDOWS;

    companion object {
        /**
         * A [Map] mapping the [OperatingSystem] enum constant to a [List] of `os.name` values to which the constant
         * should be mapped.
         */
        private val operatingSystemNameMap = mapOf(
            LINUX to listOf("Linux"),
            MAC_OS_X to listOf("Mac OS X"),
            WINDOWS to listOf(
                "Windows 95",
                "Windows 98",
                "Windows Me",
                "Windows 9X (unknown)",
                "Windows 2000",
                "Windows 2003",
                "Windows XP",
                "Windows NT (unknown)",
                "Windows Vista",
                "Windows 7",
                "Windows 8",
                "Windows 8.1",
                "Windows 10",
                "Windows 11",
                "Windows Server 2008",
                "Windows Server 2008 R2",
                "Windows Server 2012",
                "Windows Server 2012 R2",
                "Windows Server 2016",
                "Windows Server 2019",
                "Windows Server 2022"
            )
        )

        /**
         * A reversed map providing the possibility to _lookup_ an [OperatingSystem] for a given `os.name` value.
         */
        private val nameOperatingSystemMap = operatingSystemNameMap.entries.map { (os, values) ->
            values.map { Pair(it, os) }
        }.flatten().associate { it }


        /**
         * @return the value of the `os.name` system property.
         * @throws RequiredSystemPropertyMissingError if the system properties do not contain the `os.name`
         */
        private fun getOsName(): String =
            System.getProperty("os.name")
                ?: throw RequiredSystemPropertyMissingError("os.name")

        /**
         * Lookup an [OperatingSystem] for a given name.
         *
         * @param name the `os.name`
         * @return the [OperatingSystem] mapped to the given `name`
         * @throws UnknownOperatingSystemError if there is no [OperatingSystem] mapped to the given `name`
         */
        private fun lookup(name: String): OperatingSystem = when (name) {
            in nameOperatingSystemMap -> nameOperatingSystemMap[name]!!
            else     -> throw UnknownOperatingSystemError(name)
        }

        /**
         * Gets the [OperatingSystem] of the current platform.
         * @return the [OperatingSystem] of the current platform.
         * @throws UnknownOperatingSystemError if the [OperatingSystem] is not known.
         * @throws RequiredSystemPropertyMissingError if the system properties do not contain the `os.name` property.
         */
        fun get(): OperatingSystem = lookup(getOsName())
    }
}
