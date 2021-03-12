import org.gradle.internal.os.OperatingSystem

plugins {
	kotlin("multiplatform") version "1.4.31" apply false
}

subprojects {
	group = "io.github.nlbuescher"

	repositories {
		mavenCentral()
	}

	extra["host"] = OperatingSystem.current()
	extra["useSingleTarget"] = System.getProperty("idea.active") == "true"
}
