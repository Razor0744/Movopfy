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

rootProject.name = "Movopfy"
include(":app")
include(":common")
include(":network")
include(":database")
include(":datastore")
include(":firebase")
include(":workManager")
include(":home")
include(":uiComponents")
include(":anime")
include(":auth")
include(":details")
include(":favourite")
include(":search")
include(":movies")
include(":player")
include(":settings")
