#include "sse.h"

__m128 sse_add_ps(__m128 a, __m128 b) { return _mm_add_ps(a, b); }
__m128 sse_sub_ps(__m128 a, __m128 b) { return _mm_sub_ps(a, b); }
__m128 sse_mul_ps(__m128 a, __m128 b) { return _mm_mul_ps(a, b); }
__m128 sse_div_ps(__m128 a, __m128 b) { return _mm_div_ps(a, b); }

__m128 sse_round_ps(__m128 x) {
	__m128 sgn0 = _mm_set1_ps(-0.0f);
	__m128 and0 = _mm_and_ps(sgn0, x);
	__m128 or0 = _mm_or_ps(and0, _mm_set1_ps(8388608));
	__m128 add0 = _mm_add_ps(x, or0);
	return _mm_sub_ps(add0, or0);
}

__m128 sse_floor_ps(__m128 x) {
	__m128 rnd0 = sse_round_ps(x);
	__m128 cmp0 = _mm_cmplt_ps(x, rnd0);
	__m128 and0 = _mm_and_ps(cmp0, _mm_set1_ps(1));
	return _mm_sub_ps(rnd0, and0);
}
