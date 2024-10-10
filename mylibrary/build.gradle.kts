plugins {
    alias(libs.plugins.android.library)
    id ("maven-publish")
}

android {
    namespace = "com.example.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            // Configuration for debug build
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}



dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}

group = "com.github.mwaqasm026"
version = "1.0.12"

publishing {
    publications {
        println("Available components:")
        components.forEach { component ->
            println(component.name)
        }
        create<MavenPublication>("mavenAndroid") {
            from(components.findByName("release"))

            pom {
                groupId = project.group.toString()
                artifactId = "ApplicationForKitpack"
                version = project.version.toString()
                // Optional: You can add additional metadata like description, developers, etc.
                description = "A brief description of my library"
                name = "ApplicationForKitpack"
                url = "https://github.com/mwaqasm026/ApplicationForKitpack"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "ApplicationForKitpack"
                        name = "M Waqas"
                        email = "m.mughal@ediyarme.com"
                    }
                }
            }
        }
    }

    repositories {
        maven {
            // Use JitPack URL
            url = uri("https://jitpack.io")

//            for the private repo to access it
//            credentials {
//                username = "YOUR_GITHUB_USERNAME"
//                password = System.getenv("GITHUB_TOKEN") // Ensure this is set correctly in your environment
//            }
        }
    }
}

//publishing {
//    publications {
//        println("Available components:")
//        components.forEach { component ->
//            println(component.name)
//
//        create<MavenPublication>("mavenAndroid") {
//            from(components.findByName("release") ?: components["debug"] ?: components["java"])
//            pom {
//                groupId = project.group.toString()
//                artifactId = "mylibrary"
//                version = project.version.toString()
//            }
//        }
//    }
//    repositories {
//        maven {
//            url = uri("com.github/mwaqasm026/ApplicationForKitpack.git")
//        }
//    }
//}
//}