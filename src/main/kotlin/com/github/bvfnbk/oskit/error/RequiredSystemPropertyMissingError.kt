package com.github.bvfnbk.oskit.error

/**
 * A [RuntimeException] to be thrown if a required [System] property is `null`.
 *
 * @param key The key of the required [System] property.
 */
class RequiredSystemPropertyMissingError(val key: String) : RuntimeException() {
}
