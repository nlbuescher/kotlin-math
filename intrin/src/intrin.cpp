#include <iostream>

// CPUID

#if !defined(_WIN32)
#include <cpuid.h>
#endif

static inline void cpuid(int info[4], int x) {
#if !defined(_WIN32)
	__cpuid_count(x, 0, info[0], info[1], info[2], info[3]);
#elif
	__cpuidex(info, x, 0);
#endif
}

// INTRINSICS

#include "intrin.h"
#include "sse.h"
#include "sse4.1.h"

__m128 (* mm_add_ps)(__m128, __m128);
__m128 (* mm_sub_ps)(__m128, __m128);
__m128 (* mm_mul_ps)(__m128, __m128);
__m128 (* mm_div_ps)(__m128, __m128);

__m128 (* mm_round_ps)(__m128);
__m128 (* mm_floor_ps)(__m128);

void loadIntrinsics() {
	int info[4];
	cpuid(info, 1);

	// SSE
	if (info[3] & bit_SSE) {
		mm_add_ps = sse_add_ps;
		mm_sub_ps = sse_sub_ps;
		mm_mul_ps = sse_mul_ps;
		mm_div_ps = sse_div_ps;
		mm_round_ps = sse_round_ps;
		mm_floor_ps = sse_floor_ps;

		std::cout << "loaded SSE" << std::endl;
	}

	// SSE4.1
	if (info[2] & bit_SSE4_1) {
		mm_round_ps = sse4_1_round_ps;
		mm_floor_ps = sse4_1_floor_ps;

		std::cout << "loaded SSE4.1" << std::endl;
	}
}
