plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios()
    iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs("src/commonMain/kotlin")
            dependencies {
                implementation(libs.datetime)
                implementation(libs.napier)
            }
        }
        val androidMain by getting {
            kotlin.srcDirs("src/androidMain/kotlin")
        }
        val iosMain by getting {
            kotlin.srcDirs("src/iosMain/kotlin")
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting
        val iosSimulatorArm64Test by getting
    }
}

android {
    namespace = "com.vivianrosa.findtime"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
