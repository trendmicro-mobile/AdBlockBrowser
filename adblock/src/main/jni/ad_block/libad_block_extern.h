#include <jni.h>
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <exception>
#include <atomic>
#include <stdio.h>
#include <vector>
#include "ad_block_client.h"
/**
 * Created by swift_gan on 2018/4/3.
 */

const int CONTENT_TYPE_NONE = 0;
const int CONTENT_TYPE_IMAGE = 1;
const int CONTENT_TYPE_STYLE = 2;
const int CONTENT_TYPE_SCRIPT = 3;

std::atomic<bool> adBlockInitStatus(false);
std::atomic<bool> privacyInitStatus(false);

std::vector<AdBlockClient*> adblock__parsers;
std::vector<AdBlockClient*> privacy__parsers;

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_initAdBlocker(JNIEnv *env, jclass type, jobjectArray ptns);

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_isAd(JNIEnv *env, jclass type, jstring url_
                                           , jstring domain_,jint contentType);

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_initPrivacyBlocker(JNIEnv *env, jclass type, jobjectArray ptns);

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_isPrivacy(JNIEnv *env, jclass type, jstring url_
        , jstring domain_,jint contentType);