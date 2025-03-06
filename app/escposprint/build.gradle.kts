plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.moven)
}

android {
    namespace = "com.gipl.escposprint"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.gipl.escposprint"
            artifactId = "escposprint"
            version = "4.2.2"
//            artifact("${layout.buildDirectory}/outputs/aar/escposprint-release.aar")
            artifact("/Users/admin/Documents/Ankit/EzyBiller/printer_library/app/escposprint/build/outputs/aar/escposprint-release.aar")
        }
    }
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/polekar-ankit/ThermalPrinter")
            credentials {
                username = "polekar-ankit"
                
            }
        }
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.core)
}