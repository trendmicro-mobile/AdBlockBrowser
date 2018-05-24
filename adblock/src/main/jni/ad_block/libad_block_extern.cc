#include "libad_block_extern.h"

/**
 * Created by swift_gan on 2018/4/3.
 */

using namespace std;


bool endWith(const char *str, const char *end) {
    bool result = false;
    if (str != NULL && end != NULL) {
        int l1 = strlen(str);
        int l2 = strlen(end);
        if (l1 >= l2) {
            if (strcmp(str + l1 - l2, end) == 0) {
                result = true;
            }
        }
    }
    return result;
}

string getFileContents(const char *filename)
{
    ifstream in(filename, ios::in);
    if (in) {
        ostringstream contents;
        contents << in.rdbuf();
        in.close();
        return(contents.str());
    }
    throw(errno);
}

void writeFile(const char *filename, const char *buffer, int length)
{
    ofstream outFile(filename, ios::out | ios::binary);
    if (outFile) {
        outFile.write(buffer, length);
        outFile.close();
        return;
    }
    throw(errno);
}

char* getData(const char *filename) {
    ifstream in (filename, ios::in|ios::binary|ios::ate);
    int size = in.tellg();
    in.seekg(0, ios::beg);
    char* buffer = new char[size];
    in.read(buffer, size);
    in.close();
    return buffer;
}

void addPtn( const char *path, std::vector<AdBlockClient*> *clients) {
    AdBlockClient *adBlockClient = new AdBlockClient();
    const char *buffer = NULL;
    char *tmp = NULL;
    try {
        if (endWith(path, ".dat")) {
            tmp = getData(path);
            adBlockClient->deserialize(tmp);
        } else {
            std::string &&esaylist = getFileContents(path);
            buffer = esaylist.c_str();
            adBlockClient->parse(buffer);
        }
        clients->push_back(adBlockClient);
//        int size;
//        // This buffer is allocate on the heap, you must call delete[] when you're done using it.
//        buffer = adBlockClient->serialize(&size);
//        std::string const& output = std::string(path) + std::string(".dat");
//        writeFile(output.c_str(), buffer, size);
    } catch (exception &e) {
        delete adBlockClient;
    }
}

jboolean initPtn(JNIEnv *env, jobjectArray ptns, std::vector<AdBlockClient*> *clients) {
    if (ptns == NULL)
        return JNI_FALSE;
    jsize size = env->GetArrayLength(ptns);
    if (size == 0)
        return JNI_FALSE;
    for (int i = 0; i < size; i++) {
        jstring jpath = (jstring) env->GetObjectArrayElement(ptns, i);
        const char *path = env->GetStringUTFChars(jpath, 0);
        addPtn(path, clients);
        env->ReleaseStringUTFChars(jpath, path);
    }
    return JNI_TRUE;
}

jboolean checkUrl(JNIEnv *env, jstring url_, jstring domain_, jint contentType, std::vector<AdBlockClient*> clients) {

    const char *url = env->GetStringUTFChars(url_, 0);
    const char *domain = env->GetStringUTFChars(domain_, 0);

    // TODO

    env->ReleaseStringUTFChars(url_, url);
    env->ReleaseStringUTFChars(domain_, domain);

    FilterOption filterOption = FONoFilterOption;

    switch (contentType) {
        case CONTENT_TYPE_NONE:
            filterOption = FONoFilterOption;
            break;
        case CONTENT_TYPE_IMAGE:
            filterOption = FOImage;
            break;
        case CONTENT_TYPE_STYLE:
            filterOption = FOStylesheet;
            break;
        case CONTENT_TYPE_SCRIPT:
            filterOption = FOScript;
            break;
    }

    for (int i = 0;i < clients.size();i ++) {
        if (clients[i]->matches(url, filterOption, domain)) {
            return JNI_TRUE;
        }
    }

    return JNI_FALSE;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_initAdBlocker(JNIEnv *env, jclass type, jobjectArray ptns) {

    if (adBlockInitStatus)
        return JNI_FALSE;

    adBlockInitStatus = true;

    return initPtn(env, ptns, &adblock__parsers);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_isAd(JNIEnv *env, jclass type, jstring url_,
                                           jstring domain_, jint contentType) {

    if (!adBlockInitStatus)
        return JNI_FALSE;

    return checkUrl(env, url_, domain_, contentType, adblock__parsers);

}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_initPrivacyBlocker(JNIEnv *env, jclass type, jobjectArray ptns) {

    if (privacyInitStatus)
        return JNI_FALSE;

    privacyInitStatus = true;

    return initPtn(env, ptns, &privacy__parsers);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_trendmicro_adblock_AdBlockerUtils_isPrivacy(JNIEnv *env, jclass type, jstring url_,
                                                jstring domain_, jint contentType) {

    if (!privacyInitStatus)
        return JNI_FALSE;

    return checkUrl(env, url_, domain_, contentType, privacy__parsers);

}