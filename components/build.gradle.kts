import Depends.Compose.implementCompose
import Depends.Hilt.implementHilt
import Depends.Core.implementCoreKtx
import Depends.Module.implementModules
import Depends.ViewModel.implementViewModel

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // =========== Modules ==============
    implementModules(Modules.common)

    // =========== Core ==============
    implementCoreKtx()

    // =========== Compose ==============
    implementCompose()

    // =========== ViewModel ==============
    implementViewModel()
}