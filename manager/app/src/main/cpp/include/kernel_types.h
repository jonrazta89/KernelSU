// Minimal kernel type definitions for userspace compilation
// Provides kernel types normally found in <linux/types.h> and <asm-generic/types.h>
// This allows userspace code to include kernel uapi headers without full kernel source

#ifndef KERNEL_TYPES_H
#define KERNEL_TYPES_H

#include <stdint.h>
#include <stddef.h>

// Basic fixed-width integer types (kernel style)
typedef int8_t  __s8;
typedef uint8_t  __u8;
typedef int16_t  __s16;
typedef uint16_t  __u16;
typedef int32_t  __s32;
typedef uint32_t  __u32;
typedef int64_t  __s64;
typedef uint64_t  __u64;

// Kernel-specific types
typedef uint32_t  __kernel_uid32_t;
typedef uint32_t  __kernel_gid32_t;
typedef uint64_t  __aligned_u64 __attribute__((aligned(8)));

// Pointer types
typedef __u32 __kernel_uint32_t;
typedef __u64 __kernel_uint64_t;

// Size and ptrdiff
typedef __kernel_uintptr_t uintptr_t;
typedef intptr_t intptr_t;

// Boolean
typedef int bool;
#define true 1
#define false 0

// NULL
#ifndef NULL
#define NULL ((void *)0)
#endif

// Kernel alignment macros
#define __aligned(x) __attribute__((aligned(x)))
#define __packed __attribute__((packed))

// Common kernel type aliases
typedef uint16_t __le16;
typedef uint32_t __le32;
typedef uint64_t __le64;
typedef uint16_t __be16;
typedef uint32_t __be32;
typedef uint64_t __be64;

// Kernel ioctl definitions
#define _IOC_NRBITS    8
#define _IOC_TYPEBITS  8
#define _IOC_SIZEBITS  14
#define _IOC_DIRBITS   2

#define _IOC_NRMASK    ((1 << _IOC_NRBITS) - 1)
#define _IOC_TYPEMASK  ((1 << _IOC_TYPEBITS) - 1)
#define _IOC_SIZEMASK  ((1 << _IOC_SIZEBITS) - 1)
#define _IOC_DIRMASK   ((1 << _IOC_DIRBITS) - 1)

#define _IOC_NRSHIFT    0
#define _IOC_TYPESHIFT  (_IOC_NRSHIFT + _IOC_NRBITS)
#define _IOC_SIZESHIFT  (_IOC_TYPESHIFT + _IOC_TYPEBITS)
#define _IOC_DIRSHIFT   (_IOC_SIZESHIFT + _IOC_SIZEBITS)

#define _IOC(dir, type, nr, size) \
    (((dir) << _IOC_DIRSHIFT) | \
     ((type) << _IOC_TYPESHIFT) | \
     ((nr) << _IOC_NRSHIFT) | \
     ((size) << _IOC_SIZESHIFT))

#define _IOC_TYPECHECK(t) (sizeof(t))

#define _IO(type, nr) _IOC(0, type, nr, 0)
#define _IOR(type, nr, size) _IOC(1, type, nr, _IOC_TYPECHECK(size))
#define _IOW(type, nr, size) _IOC(2, type, nr, _IOC_TYPECHECK(size))
#define _IOWR(type, nr, size) _IOC(3, type, nr, _IOC_TYPECHECK(size))
#define _IOR_BAD(type, nr, size) _IOC(1, type, nr, sizeof(size))
#define _IOW_BAD(type, nr, size) _IOC(2, type, nr, sizeof(size))
#define _IOWR_BAD(type, nr, size) _IOC(3, type, nr, sizeof(size))

#endif // KERNEL_TYPES_H
