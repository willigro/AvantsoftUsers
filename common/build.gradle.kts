import Depends.Hilt.implementHilt
import Depends.Core.implementCoreKtx
import Depends.ViewModel.implementViewModel

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // =========== Core ==============
    implementCoreKtx()

    // =========== Hilt ==============
    implementHilt()

    // =========== ViewModel ==============
    implementViewModel()
}