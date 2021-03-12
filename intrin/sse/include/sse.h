#ifndef CPP_TEST_SSE_H
#define CPP_TEST_SSE_H

#include <xmmintrin.h>

__m128 sse_add_ps(__m128 a, __m128 b);
__m128 sse_sub_ps(__m128 a, __m128 b);
__m128 sse_mul_ps(__m128 a, __m128 b);
__m128 sse_div_ps(__m128 a, __m128 b);
__m128 sse_round_ps(__m128 a);
__m128 sse_floor_ps(__m128 a);

#endif //CPP_TEST_SSE_H
