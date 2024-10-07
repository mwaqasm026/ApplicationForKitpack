import io.grpc.internal.SharedResourceHolder.release

plugins {
    id ("maven-publish")
    alias(libs.plugins.android.library)
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

group = "com.example.mylibrary"
version = "1.0.0"

publishing {
    publications {
        println("Available components:")
        components.forEach { component ->
            println(component.name)

        create<MavenPublication>("mavenAndroid") {
            from(components.findByName("release") ?: components["debug"] ?: components["java"])
            pom {
                groupId = project.group.toString()
                artifactId = "mylibrary"
                version = project.version.toString()
            }
        }
    }
    repositories {
        maven {
            url = uri("https://github.com/mwaqasm026/ApplicationForKitpack.git")
        }
    }
}
}