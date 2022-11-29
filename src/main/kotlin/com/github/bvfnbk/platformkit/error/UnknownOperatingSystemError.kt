package com.github.bvfnbk.platformkit.error

/**
 * A [RuntimeException] to be thrown if no [com.github.bvfnbk.platformkit.OperatingSystem] could be determined for the given
 * operating system name.
 *
 * @param name The unknown operating system name.
 */
class UnknownOperatingSystemError(val name: String) : RuntimeException() {
}
