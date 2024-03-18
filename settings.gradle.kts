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
//enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "Cats"
include (":app")
include (":domain")
include (":data")
include (":presentation:cats")
include (":presentation:uikit")
include (":presentation:home")
include(":core")
include(":presentation:pokemons")
include(":presentation:menu")
