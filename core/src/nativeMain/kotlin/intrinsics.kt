import kotlinx.cinterop.*
import platform.intrin.*
import kotlin.math.*

operator fun Vector128.plus(other: Vector128): Vector128 {
	return mm_add_ps?.invoke(this, other) ?: run {
		println("fallback")
		vectorOf(
			getFloatAt(0) + other.getFloatAt(0),
			getFloatAt(1) + other.getFloatAt(1),
			getFloatAt(2) + other.getFloatAt(2),
			getFloatAt(3) + other.getFloatAt(3)
		)
	}
}

operator fun Vector128.minus(other: Vector128): Vector128 {
	return mm_sub_ps?.invoke(this, other) ?: run {
		println("fallback")
		vectorOf(
			getFloatAt(0) - other.getFloatAt(0),
			getFloatAt(1) - other.getFloatAt(1),
			getFloatAt(2) - other.getFloatAt(2),
			getFloatAt(3) - other.getFloatAt(3)
		)
	}
}

operator fun Vector128.times(other: Vector128): Vector128 {
	return mm_mul_ps?.invoke(this, other) ?: run {
		println("fallback")
		vectorOf(
			getFloatAt(0) * other.getFloatAt(0),
			getFloatAt(1) * other.getFloatAt(1),
			getFloatAt(2) * other.getFloatAt(2),
			getFloatAt(3) * other.getFloatAt(3)
		)
	}
}

operator fun Vector128.div(other: Vector128): Vector128 {
	return mm_div_ps?.invoke(this, other) ?: run {
		println("fallback")
		vectorOf(
			getFloatAt(0) / other.getFloatAt(0),
			getFloatAt(1) / other.getFloatAt(1),
			getFloatAt(2) / other.getFloatAt(2),
			getFloatAt(3) / other.getFloatAt(3)
		)
	}
}

operator fun Vector128.rem(other: Vector128): Vector128 = when {
	//Intrinsics.SSE -> {
	//	val div0 = div_ps(this, other)
	//	val flr0 = floor(div0)
	//	val mul0 = mul_ps(other, flr0)
	//	sub_ps(this, mul0)
	//}
	else -> vectorOf(
		getFloatAt(0) % other.getFloatAt(0),
		getFloatAt(1) % other.getFloatAt(1),
		getFloatAt(2) % other.getFloatAt(2),
		getFloatAt(3) % other.getFloatAt(3)
	)
}

fun round(x: Vector128): Vector128 {
	return mm_round_ps?.invoke(x) ?: run {
		println("fallback")
		vectorOf(
			round(x.getFloatAt(0)),
			round(x.getFloatAt(1)),
			round(x.getFloatAt(2)),
			round(x.getFloatAt(3))
		)
	}
}

fun floor(x: Vector128): Vector128 {
	return mm_floor_ps?.invoke(x) ?: run {
		println("fallback")
		vectorOf(
			floor(x.getFloatAt(0)),
			floor(x.getFloatAt(1)),
			floor(x.getFloatAt(2)),
			floor(x.getFloatAt(3))
		)
	}
}
