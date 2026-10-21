# SD Maid SE FOSS Morphe patch

This Morphe patch unlocks the Pro feature gates in the official GitHub FOSS APK
(`eu.darken.sdmse`). It is adapted from the earlier Doom and Paresh SD Maid SE
patch strategies for the FOSS distribution. Their existing patches target
Google Play packages, whose entitlement implementation is different.

The patch changes the Boolean value stored in `UpgradeRepoFoss.Info.isPro` when
an entitlement state is created. It has no version-number restriction: the
fingerprint locates the field write in the constructor, even if its parameters
change. If a later release changes this entitlement structure, patching fails
instead of silently modifying another instruction. New releases still need
APK-level testing before they can be confirmed as working.

Use `python tools/verify_apk.py path/to/release.apk` to check the structural
anchor of any newer official FOSS release before patching. This check does not
replace an installation and feature test on Android.
The GitHub Actions workflow checks the newest official FOSS release daily. It
builds the `.mpp`, applies it to that APK with Morphe Desktop, and verifies the
injected bytecode. It also builds on pushes and attaches the bundle to tagged
GitHub releases. The build
first uses the repository's Actions token to read Morphe's GitHub Packages;
if GitHub denies that cross-repository package read, add a repository secret
named `MORPHE_PACKAGES_TOKEN` with `read:packages` access.

## Build

Use JDK 21 and Gradle with access to Morphe's GitHub Packages registry. Set
`GITHUB_ACTOR` and `GITHUB_TOKEN` (a token with `read:packages`), then run
`./gradlew :patches:buildAndroid`. You can also set `gpr.user` and `gpr.key` in
Gradle properties. On Windows, `./build.ps1` configures the portable JDK in
this workspace and a short temporary path for Gradle. It uses a project-local
Gradle user directory, so use environment variables with that script.
Download [the compiled `patches-0.1.0.mpp`](dist/patches-0.1.0.mpp), or build
`patches/build/libs/patches-*.mpp` locally. Import the bundle into Morphe and select **Unlock Pro (FOSS)**
for an [official FOSS APK](https://github.com/d4rken-org/sdmaid-se/releases).
The published `.mpp` has SHA-256
`1cc4077696ad5f9a0ae6775196da0da14e66cfb06116f2c447c1662175a30c62`.

This project does not target the Google Play APK/AAB variant. Install the
patched APK under Morphe's signing key, not over an APK signed by the developer.

## Verification

The official `v2.1.0-rc0`, `v2.2.0-rc0`, `v2.2.1-rc0`, and `v2.2.2-rc0` FOSS
APKs pass the structural check. The `v2.2.2-rc0` APK has SHA-256
`11b5b9084c27ab3b30db47c92f403129244c178e81e07c0bb0ffbb64a9bb1bb6`.
The bundle was built and applied successfully to the official `v2.2.2-rc0`
FOSS APK using Morphe Desktop 1.18.1. The resulting APK's DEX contains the
injected `true` instruction. Installation and feature behavior on an Android
device have not been tested.

SD Maid SE and the reference patches are GPLv3 licensed. The approach was
adapted from [Doom's patch](https://github.com/rushiranpise/morphe-patches)
and [Paresh's patch](https://gitlab.com/Paresh-Maheshwari/paresh-patches).
