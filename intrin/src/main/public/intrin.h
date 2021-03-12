#ifndef CPP_TEST_INTRIN_H
#define CPP_TEST_INTRIN_H

#include <xmmintrin.h>

#ifdef __cplusplus
extern "C" {
#endif

extern __m128 (* mm_add_ps)(__m128, __m128);
extern __m128 (* mm_sub_ps)(__m128, __m128);
extern __m128 (* mm_mul_ps)(__m128, __m128);
extern __m128 (* mm_div_ps)(__m128, __m128);

extern __m128 (* mm_round_ps)(__m128);
extern __m128 (* mm_floor_ps)(__m128);

void loadIntrinsics();

#ifdef __cplusplus
}
#endif

#endif //CPP_TEST_INTRIN_H
