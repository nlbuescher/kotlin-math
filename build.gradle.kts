import org.gradle.internal.os.OperatingSystem

plugins {
	kotlin("multiplatform") version "1.4.31"
}

repositories {
	mavenCentral()
}

val host: OperatingSystem = OperatingSystem.current()

kotlin {
	val nativeTarget = when {
		host.isLinux -> linuxX64("native")
		host.isMacOsX -> macosX64("native")
		host.isWindows -> mingwX64("native")
		else -> error("unknown host")
	}

	nativeTarget.apply {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
		compilations.named("main") {
			compileKotlinTask.dependsOn(":intrin:assembleRelease")
			compileKotlinTask.dependsOn(":intrin:sse:assembleRelease")
			compileKotlinTask.dependsOn(":intrin:sse4.1:assembleRelease")

			cinterops.create("intrin") {
				includeDirs(project("intrin").projectDir.resolve("include"))
			}

			kotlinOptions.freeCompilerArgs = listOf(
				"-include-binary", "intrin/build/lib/main/release/libintrin.a",
				"-include-binary", "intrin/sse/build/lib/main/release/libsse.a",
				"-include-binary", "intrin/sse4.1/build/lib/main/release/libsse4.1.a"
			)
		}
	}
}
