plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    kotlin("multiplatform")
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composePlugin)
}
//
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))
                api(compose.foundation)
                api(compose.runtime)
                api(compose.material)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.ui)
                api(compose.uiTooling)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

android {
    namespace = "com.vivianrosa.findtime.android"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}