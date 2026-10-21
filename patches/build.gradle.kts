group = "local.sdmaidse.morphe"

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

patches {
    about {
        name = "SD Maid SE Morphe Patch"
        description = "Local Unlock Pro patch for the official SD Maid SE FOSS APK"
        source = "https://github.com/gggg-unit/sdmaid-se-morphe-patch"
        author = "gggg-unit"
        contact = "https://github.com/gggg-unit/sdmaid-se-morphe-patch/issues"
        website = "https://github.com/gggg-unit/sdmaid-se-morphe-patch/releases"
        license = "GPLv3"
    }
}
