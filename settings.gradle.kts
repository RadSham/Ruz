pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ruz"
include(":app")
include(":feature:splash")
include(":feature:home")
include(":core_api")
include(":feature:newevent")
include(":main")
include(":core_api_impl")
include(":feature:eventdetails")
include(":feature:auth")
include(":feature:account")
include(":feature:signin")
include(":feature:checkuser")
include(":feature:iamin")
