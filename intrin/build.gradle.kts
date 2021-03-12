plugins {
	`cpp-library`
}

library {
	linkage.set(listOf(Linkage.STATIC))

	source.from("src")
	publicHeaders.from("include")

	dependencies {
		api(project("sse"))
		api(project("sse4.1"))
	}
}
