import kotlin.test.*

@Test
fun main() {
	println(Float4(1f) + Float4(2f))
	println(Float4(1f) - Float4(2f))
	println(Float4(1f) * Float4(2f))
	println(Float4(1f) / Float4(2f))
	println(Float4(1f) % Float4(2f))
	println(round(Float4(1.5f)))
	println(floor(Float4(1.5f)))
}
