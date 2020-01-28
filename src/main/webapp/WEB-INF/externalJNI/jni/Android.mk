LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ex1
LOCAL_SRC_FILES := ex1.cpp ./jni_help.cpp ./zhc.cpp
include $(BUILD_SHARED_LIBRARY)