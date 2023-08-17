import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

const val IMPLEMENTATION = "implementation"
const val TEST_IMPLEMENTATION = "testImplementation"
const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
const val DEBUG_IMPLEMENTATION = "androidTestImplementation"
const val KAPT = "kapt"
const val KAPT_ANDROID_TEST = "kaptTest"
const val ANNOTATION_PROCESSOR = "annotationProcessor"
const val DEBUG_IMPLEMENTARION = "debugImplementation"

fun DependencyHandler.implement(url: String) {
    add(IMPLEMENTATION, url)
}

fun DependencyHandler.debugImplementation(url: String) {
    add(DEBUG_IMPLEMENTARION, url)
}

fun DependencyHandler.testImplement(url: String) {
    add(TEST_IMPLEMENTATION, url)
}

fun DependencyHandler.androidTestImplement(url: String) {
    add(ANDROID_TEST_IMPLEMENTATION, url)
}

fun DependencyHandler.debugImplement(url: String) {
    add(DEBUG_IMPLEMENTATION, url)
}

fun DependencyHandler.kapt(url: String) {
    add(KAPT, url)
}

fun DependencyHandler.kaptAndroidTest(url: String) {
    add(KAPT_ANDROID_TEST, url)
}

fun DependencyHandler.annotationProcessor(url: String) {
    add(ANNOTATION_PROCESSOR, url)
}

object Depends {

    object Module {
        fun DependencyHandler.implementAllModules(vararg less: String) {
            val result = Modules.modules - less
            result.forEach { add(IMPLEMENTATION, project(it)) }
        }

        fun DependencyHandler.implementModules(vararg modules: String) {
            modules.forEach { add(IMPLEMENTATION, project(it)) }
        }

        fun DependencyHandler.androidTestImplementationModules(vararg modules: String) {
            modules.forEach { add(ANDROID_TEST_IMPLEMENTATION, project(it)) }
        }

        fun DependencyHandler.testImplementationModules(vararg modules: String) {
            modules.forEach { add(TEST_IMPLEMENTATION, project(it)) }
        }
    }

    object Gradle {
        fun getGradlePlugin() = "com.android.tools.build:gradle:${Versions.GRADLE_TOOL_BUILD}"
        fun getKotlinPlugin() =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
    }

    object Core {
        fun DependencyHandler.implementCoreKtx() {
            implement("androidx.core:core-ktx:1.7.0")
            implement("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
        }
    }

    object ViewModel {
        fun DependencyHandler.implementViewModel() {
            implement("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEW_MODEL}")
            implement("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.VIEW_MODEL}")
        }
    }

    object Coroutine {
        fun DependencyHandler.implementCoroutines() {
            implement("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")
        }
    }

    object Retrofit {
        fun DependencyHandler.implementRetrofit() {
            implement("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
            implement("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_GSON_CONVERTER}")
            implement("com.google.code.gson:gson:${Versions.GOOGLE_GSON}")

            implement("com.squareup.okhttp3:logging-interceptor:${Versions.SQUAREUP_OK_HTTP_3_LOGGING_INTERCEPTOR}")
            implement("com.squareup.okhttp3:okhttp:4.9.3")
        }
    }

    object Compose {
        fun DependencyHandler.implementCompose() {
            implement("androidx.constraintlayout:constraintlayout-compose:${Versions.CONSTRAINT_COMPOSE}")
            implement("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
            implement("androidx.navigation:navigation-compose:2.5.3")
            add(IMPLEMENTATION, platform("androidx.compose:compose-bom:2023.04.01"))
            implement("androidx.activity:activity-compose:1.6.1")
            implement("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
            implement("androidx.compose.runtime:runtime-livedata")
            implement("androidx.compose.material3:material3")
            implement("androidx.compose.ui:ui-tooling-preview")
            debugImplementation("androidx.compose.ui:ui-tooling")
        }
    }

    object Hilt {
        fun DependencyHandler.implementHilt() {
            implement("com.google.dagger:hilt-android:${Versions.HILT}")
            kapt("com.google.dagger:hilt-compiler:${Versions.HILT}")
        }
    }

    object Plugins {
        const val HILT = "com.google.dagger.hilt.android"
        const val CLASS_PATH_HILT =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_PLUGIN}"
    }
}