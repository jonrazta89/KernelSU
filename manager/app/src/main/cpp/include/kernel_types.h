// Minimal kernel type definitions for userspace compilation
// Provides types needed by kernel uapi headers that may not be available
// in the Android NDK sysroot for the target architecture.
// NOTE: Does NOT redefine _IOC/_IO/_IOR/_IOW/_IOWR macros - those come from
// <linux/ioctl.h>. Does NOT redefine __u32/__u64 etc. - those come from
// <linux/types.h> via asm-generic headers.

#ifndef KERNEL_TYPES_H
#define KERNEL_TYPES_H

#include <stdint.h>
#include <stddef.h>

// Only define types that may NOT be present in all NDK sysroot configurations.
// __u32, __u64 etc. are typically defined by <linux/types.h> via asm-generic.
// But in case they're missing, provide them as fallback.

#ifndef __u8
typedef uint8_t  __u8;
#endif
#ifndef __u16
typedef uint16_t __u16;
#endif
#ifndef __u32
typedef uint32_t __u32;
#endif
#ifndef __u64
typedef uint64_t __u64;
#endif

#ifndef __s8
typedef int8_t   __s8;
#endif
#ifndef __s16
typedef int16_t  __s16;
#endif
#ifndef __s32
typedef int32_t  __s32;
#endif
#ifndef __s64
typedef int64_t  __s64;
#endif

#ifndef __aligned_u64
#define __aligned_u64 __u64 __attribute__((aligned(8)))
#endif

#ifndef __kernel_uid32_t
typedef uint32_t __kernel_uid32_t;
#endif
#ifndef __kernel_gid32_t
typedef uint32_t __kernel_gid32_t;
#endif

#endif // KERNEL_TYPES_H
