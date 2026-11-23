// Minimal kernel type definitions for userspace compilation
// Provides types needed by kernel uapi headers that may not be available
// in the Android NDK sysroot for the target architecture.
// NOTE: Does NOT redefine _IOC/_IO/_IOR/_IOW/_IOWR macros - those come from
// <linux/ioctl.h>. Does NOT redefine __u32/__u64 etc. - those come from
// <linux/types.h> via asm-generic headers.

#ifndef KERNEL_TYPES_H
#define KERNEL_TYPES_H

// Kernel types (__u32, __u64, __aligned_u64, etc.) are already provided by
// <linux/types.h> via the NDK sysroot's asm-generic headers. This header is
// intentionally minimal to avoid redefinition conflicts. It only provides
// fallback definitions for types that might not be present.

// __aligned_u64 is defined in <linux/types.h> as:
//   __aligned_u64 __u64 __attribute__((aligned(8)))
// But if it's missing, provide it:
#ifndef __aligned_u64
#define __aligned_u64 __u64 __attribute__((aligned(8)))
#endif

#endif // KERNEL_TYPES_H
