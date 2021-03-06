#include <odin.h>
#include <ussr_internal.h>

#include <nativeController.h>

#include <stdlib.h>

char sendMessage(USSREnv *env, unsigned char *message, unsigned char messageSize, unsigned char connector) {
  jbyteArray array = ussr_charArray2byteArray(env, message, messageSize);
  char result = ussr_call_byte_controller_method(env, "sendMessage", "([BBB)B", array, messageSize, connector);
  ussr_releaseByteArray(env, array);
  return result;
}

void JNICALL Java_ussr_samples_odin_OdinNativeController_nativeHandleMessage(JNIEnv *jniENV, jobject self, jint initializationContext, jbyteArray message, jint messageSize, jint channel) {
  unsigned char buffer[ATRON_MAX_MESSAGE_SIZE];
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;
  ussr_byteArray2charArray(&env, message, messageSize, buffer);
  handleMessage(&env, buffer, messageSize, channel);
}

void delay(int amount) {
#ifdef WIN32
  fprintf(stderr,"Warning: cannot sleep\n");
#else
  sleep(amount/100);
#endif
}
