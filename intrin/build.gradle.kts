import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	id("cpp-library")
	kotlin("multiplatform")
}

group = "io.github.nlbuescher"
version = "0.0.1-SNAPSHOT"

library {
	linkage.set(listOf(Linkage.STATIC))

	dependencies {
		implementation(project("sse"))
		implementation(project("sse4.1"))
	}
}

val host: OperatingSystem by extra
val useSingleTarget: Boolean by extra

kotlin {
	if (!useSingleTarget || host.isLinux) linuxX64()
	if (!useSingleTarget || host.isMacOsX) macosX64()
	if (!useSingleTarget || host.isWindows) mingwX64()

	targets.withType<KotlinNativeTarget> {
		compilations.named("main") {
			compileKotlinTask.dependsOn(":intrin:assembleRelease")
			compileKotlinTask.dependsOn(":intrin:sse:assembleRelease")
			compileKotlinTask.dependsOn(":intrin:sse4.1:assembleRelease")

			cinterops.create("intrin") {
				includeDirs(projectDir.resolve("src/main/public"))
			}

			kotlinOptions.freeCompilerArgs = listOf(
				"-include-binary", project(":intrin").buildDir.resolve("lib/main/release/libintrin.a").path,
				"-include-binary", project(":intrin:sse").buildDir.resolve("lib/main/release/libsse.a").path,
				"-include-binary", project(":intrin:sse4.1").buildDir.resolve("lib/main/release/libsse4.1.a").path
			)
		}
	}

	sourceSets {
		targets.withType<KotlinNativeTarget> {
			get("${name}Main").run {
				kotlin.srcDir("src/nativeMain/kotlin")
				resources.srcDir("src/nativeMain/resources")
			}
			get("${name}Test").run {
				kotlin.srcDir("src/nativeTest/kotlin")
				resources.srcDir("src/nativeTest/resources")
			}
		}
	}
}
