package Specs

import geb.spock.GebReportingSpec

class BaseSpec extends GebReportingSpec {

    private static final String BASE_URL = "https://www.demoblaze.com/"

    def setup() {
        go(BASE_URL)
    }

    def cleanup() {
        // Clear local & session storage
        clearWebStorage()
        // Clear cookies
        clearCookies()
    }
}
