//
// Created by root on 19-10-2.
//

#ifndef JNI_JNI_HELP_H
#define JNI_JNI_HELP_H

#endif //JNI_JNI_HELP_H


#include <jni.h>
#include <cstddef>
#include "zhc.h"

void Log(JNIEnv *env, const char *tag, const char *s);

void LogArr(JNIEnv *env, const char *tag, const char *s, int size);