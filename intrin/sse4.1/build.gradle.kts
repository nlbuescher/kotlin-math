plugins {
	`cpp-library`
}

library {
	linkage.set(listOf(Linkage.STATIC))

	source.from("src")
	publicHeaders.from("include")

	tasks.withType<CppCompile> {
		compilerArgs.add("-msse4.1")
	}
}
