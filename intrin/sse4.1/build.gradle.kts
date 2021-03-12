plugins {
	`cpp-library`
}

library {
	linkage.set(listOf(Linkage.STATIC))

	tasks.withType<CppCompile> {
		compilerArgs.add("-msse4.1")
	}
}
