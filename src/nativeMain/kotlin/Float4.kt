import kotlin.intrin.*
import kotlin.math.*

data class Float4(
	var x: Float,
	var y: Float,
	var z: Float,
	var w: Float
) {
	constructor(v: Vector128) : this(v.getFloatAt(0), v.getFloatAt(1), v.getFloatAt(2), v.getFloatAt(3))
	constructor(scalar: Float = 0f) : this(scalar, scalar, scalar, scalar)

	operator fun get(index: Int): Float = when (index) {
		0 -> x; 1 -> y; 2 -> z; 3 -> w
		else -> throw IndexOutOfBoundsException()
	}

	operator fun set(index: Int, value: Float) = when (index) {
		0 -> x = value; 1 -> y = value; 2 -> z = value; 3 -> w = value
		else -> throw IndexOutOfBoundsException()
	}

	operator fun unaryPlus(): Float4 = copy()
	operator fun unaryMinus(): Float4 = Float4(-x, -y, -z, -w)

	operator fun plus(scalar: Float): Float4 = Float4(
		vectorOf(x, y, z, w) + vectorOf(scalar, scalar, scalar, scalar)
	)

	operator fun minus(scalar: Float): Float4 = Float4(
		vectorOf(x, y, z, w) - vectorOf(scalar, scalar, scalar, scalar)
	)

	operator fun times(scalar: Float): Float4 = Float4(
		vectorOf(x, y, z, w) * vectorOf(scalar, scalar, scalar, scalar)
	)

	operator fun div(scalar: Float): Float4 = Float4(
		vectorOf(x, y, z, w) / vectorOf(scalar, scalar, scalar, scalar)
	)

	operator fun rem(scalar: Float): Float4 = Float4(
		vectorOf(x, y, z, w) % vectorOf(scalar, scalar, scalar, scalar)
	)

	operator fun plus(other: Float4): Float4 = Float4(
		vectorOf(x, y, z, w) + vectorOf(other.x, other.y, other.z, other.w)
	)

	operator fun minus(other: Float4): Float4 = Float4(
		vectorOf(x, y, z, w) - vectorOf(other.x, other.y, other.z, other.w)
	)

	operator fun times(other: Float4): Float4 = Float4(
		vectorOf(x, y, z, w) * vectorOf(other.x, other.y, other.z, other.w)
	)

	operator fun div(other: Float4): Float4 = Float4(
		vectorOf(x, y, z, w) / vectorOf(other.x, other.y, other.z, other.w)
	)

	operator fun rem(other: Float4): Float4 = Float4(
		vectorOf(x, y, z, w) % vectorOf(other.x, other.y, other.z, other.w)
	)

	operator fun plusAssign(scalar: Float) {
		val v = vectorOf(x, y, z, w) + vectorOf(scalar, scalar, scalar, scalar)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun minusAssign(scalar: Float) {
		val v = vectorOf(x, y, z, w) - vectorOf(scalar, scalar, scalar, scalar)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun timesAssign(scalar: Float) {
		val v = vectorOf(x, y, z, w) * vectorOf(scalar, scalar, scalar, scalar)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun divAssign(scalar: Float) {
		val v = vectorOf(x, y, z, w) / vectorOf(scalar, scalar, scalar, scalar)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun remAssign(scalar: Float) {
		val v = vectorOf(x, y, z, w) % vectorOf(scalar, scalar, scalar, scalar)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}


	operator fun plusAssign(other: Float4) {
		val v = vectorOf(x, y, z, w) + vectorOf(other.x, other.y, other.z, other.w)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun minusAssign(other: Float4) {
		val v = vectorOf(x, y, z, w) - vectorOf(other.x, other.y, other.z, other.w)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun timesAssign(other: Float4) {
		val v = vectorOf(x, y, z, w) * vectorOf(other.x, other.y, other.z, other.w)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun divAssign(other: Float4) {
		val v = vectorOf(x, y, z, w) / vectorOf(other.x, other.y, other.z, other.w)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	operator fun remAssign(other: Float4) {
		val v = vectorOf(x, y, z, w) % vectorOf(other.x, other.y, other.z, other.w)
		x = v.getFloatAt(0); y = v.getFloatAt(1); z = v.getFloatAt(2); w = v.getFloatAt(3)
	}

	val squareMagnitude: Float get() = this dot this
	val magnitude: Float get() = sqrt(this dot this)

	/** Returns the dot product of this vector and [other]. */
	infix fun dot(other: Float4): Float = x * other.x + y * other.y + z * other.z + w * other.w

	fun normalize(): Float4 = this / magnitude

	override fun toString(): String = "($x, $y, $z, $w)"
}

fun Float4.toFloatArray(): FloatArray = floatArrayOf(x, y, z, w)

fun Float4.toFloat4(): Float4 = copy()

fun round(x: Float4): Float4 = Float4(round(vectorOf(x.x, x.y, x.z, x.w)))

fun floor(x: Float4): Float4 = Float4(floor(vectorOf(x.x, x.y, x.z, x.w)))

/** Returns the distance between [from] and [to]. */
fun distance(from: Float4, to: Float4): Float {
	val xDiff = from.x - to.x
	val yDiff = from.y - to.y
	val zDiff = from.z - to.z
	val wDiff = from.w - to.w
	return sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff + wDiff * wDiff)
}

/** Linearly interpolates between vectors [from] and [to] by [ratio]. */
fun lerp(from: Float4, to: Float4, ratio: Float): Float4 {
	return Float4(
		from.x * (1 - ratio) + to.x * ratio,
		from.y * (1 - ratio) + to.y * ratio,
		from.z * (1 - ratio) + to.z * ratio,
		from.w * (1 - ratio) + to.w * ratio
	)
}

/** Returns a new vector made from the largest components of [a] and [b]. */
fun max(a: Float4, b: Float4): Float4 {
	return Float4(max(a.x, b.x), max(a.y, b.y), max(a.z, b.z), max(a.w, b.w))
}

/** Returns a new vector made from the smallest components of [a] and [b]. */
fun min(a: Float4, b: Float4): Float4 {
	return Float4(min(a.x, b.x), min(a.y, b.y), min(a.z, b.z), min(a.w, b.w))
}


operator fun Float.plus(vector: Float4): Float4 = Float4(this).also { it += vector }
operator fun Float.minus(vector: Float4): Float4 = Float4(this).also { it -= vector }
operator fun Float.times(vector: Float4): Float4 = Float4(this).also { it *= vector }
operator fun Float.div(vector: Float4): Float4 = Float4(this).also { it /= vector }
operator fun Float.rem(vector: Float4): Float4 = Float4(this).also { it %= vector }
