LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ex
LOCAL_SRC_FILES := ex.c
include $(BUILD_SHARED_LIBRARY)