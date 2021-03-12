import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	kotlin("multiplatform")
}

val host: OperatingSystem by extra
val useSingleTarget: Boolean by extra

kotlin {
	if (!useSingleTarget || host.isLinux) linuxX64()
	if (!useSingleTarget || host.isMacOsX) macosX64()
	if (!useSingleTarget || host.isWindows) mingwX64()

	targets.withType<KotlinNativeTarget> {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
	}

	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":intrin"))
			}
		}

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
