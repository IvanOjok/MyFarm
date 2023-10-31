//import com.google.protobuf.gradle.protoc
//import com.google.protobuf.gradle.generateProtoTasks
//import com.google.protobuf.gradle.id


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf")
    id("kotlin-kapt")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.ivanojok.myfarm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ivanojok.myfarm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val nav_version = "2.7.4"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")

    //circle view
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //preference datastores
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    //protodatastores
    implementation("androidx.datastore:datastore:1.0.0")
    //implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.19.2")
    //implementation("com.google.protobuf:protobuf-kotlin:3.19.4")

    //room
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

protobuf {
    protoc { artifact = "com.google.protobuf:protoc:3.19.2" }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
//    generateProtoTasks {
//        all().forEach {
//                task -> task.builtins {
//                java { "lite" }
//            }
//        }
//    }
}

//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:21.7"
//    }
//
//    // Generates the java Protobuf-lite code for the Protobufs in this project. See
//    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
//    // for more information.
//    generateProtoTasks {
//        all().each { task ->
//            task.builtins {
//                java {
//                    option("lite")
//                }
//            }
//        }
//    }
//}