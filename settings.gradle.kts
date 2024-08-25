pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "EffMobContest"
include(":app")
include(":core")
include(":feature-profile")
include(":feature-messages")
include(":feature-responses")
include(":feature-favorite-vacancies")
include(":feature-search-vacancies")
include(":feature-login")
include(":feature-vacancy-details")
include(":core:network")
include(":core:data")
