#include "sse4.1.h"

__m128 sse4_1_round_ps(__m128 x) { return _mm_round_ps(x, _MM_FROUND_NEARBYINT); }
__m128 sse4_1_floor_ps(__m128 x) { return _mm_floor_ps(x); }
