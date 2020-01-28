#include "./ex1.h"
#include <thread>

auto *g_vm = (JavaVM *) nullptr;

void *f(void *) {
//    auto env = (JNIEnv *) nullptr;
//    g_vm->AttachCurrentThread(&env, nullptr);
    /*jobject activity = env->NewLocalRef(g_activity);
    jclass clz = env->FindClass("pers/zhc/tools/utils/ToastUtils");
    jmethodID mid = env->GetStaticMethodID(clz, "show", "(Landroid/content/Context;Ljava/lang/CharSequence;)V");
    jstring jstr = env->NewStringUTF("jni...");
    env->CallStaticVoidMethod(clz, mid, activity, jstr);*/
    JNIEnv *l_env;
    g_vm->AttachCurrentThread(&l_env, nullptr);
    Log(l_env, "", "1");
    FILE *fp = nullptr;
    Log(l_env, "", "2");
    system("su");
    Log(l_env, "", "su");
    if ((fp = popen("getevent", "r")) == nullptr) {
        Log(l_env, "input event", "popen error.");
        perror("popen error.");
        return nullptr;
    }
    Log(l_env, "", "6");
    char buf[1024];
    buf[sizeof(buf) - 1] = '\0';
    Log(l_env, "", "7");
    while (fgets(buf, sizeof(buf) - 1, fp) != nullptr) {
        Log(l_env, "input event", "wait...");
        Log(l_env, "input event", buf);
    }
    g_vm->DetachCurrentThread();
    return nullptr;
}

JNIEXPORT void JNICALL Java_pers_zhc_tools_utils_ExternalJNI_ex1
        (JNIEnv *env, jobject activity) {

}