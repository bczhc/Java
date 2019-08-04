//
// Created by root on 19-7-9.
//
#include <stdio.h>
#include <dlfcn.h>

int main() {
    int (*f)(int a1);
    void *handle = dlopen("/home/zhc/code/code/java/src/test/libs/x86_64/liba.so", RTLD_LAZY);
    f = dlsym(handle, "f0");
    printf("%i\n", f(2));
    dlclose(handle);
}