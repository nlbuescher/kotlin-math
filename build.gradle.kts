import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	kotlin("multiplatform") version "1.7.10"
}

repositories {
	mavenCentral()
}

val host: OperatingSystem = OperatingSystem.current()
val useSingleTarget: Boolean = System.getProperty("idea.active") == "true"

kotlin {
	if (!useSingleTarget || host.isLinux) linuxX64()
	if (!useSingleTarget || host.isMacOsX) macosX64()
	if (!useSingleTarget || host.isWindows) mingwX64()

	sourceSets {
		commonMain {
			dependencies {
				implementation("io.github.nlbuescher:kotlin-intrin:1.0.0")
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
