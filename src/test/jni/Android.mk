LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := a
LOCAL_SRC_FILES := a.c
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)