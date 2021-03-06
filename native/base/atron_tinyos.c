#include <atron_tinyos.h>
#include <ussr_internal.h>

#include <nativeController.h>

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <stdint.h>

#include "auto_generated_dispatcher_macros.h"

void activate(USSRONLY(USSREnv *env)) {
  int moduleId = ussr_call_int_controller_method(env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_MAIN__

}

int32_t initialize(USSRONLY(USSREnv *env)) {
  return 0;/*initializationContext (could we use it to disambiguate controllers in lieu of the role?)*/
}

/* API downcalls/commands */
void ussr_stub(USSREnv *env) {
  ussr_call_void_controller_method(env, "stubCall", "()V");
}
void printf_to_system_out_print(USSREnv *env, char* buf) {
  jstring str = ussr_charArray2string(env, buf);
  ussr_call_void_controller_method(env, "printfFromC", "(Ljava/lang/String;)V", str);
}
int getRandomNumber(USSREnv *env) {
  int random = ussr_call_int_controller_method(env, "getRandomNumber", "()I");
  return random;
}

//this does not really work ... !
void setPositionCentralJoint(USSREnv *env, int32_t position) {
  ussr_call_void_controller_method(env, "setPositionCentralJoint", "(I)V", position);
}


void setSpeedCentralJoint(USSREnv *env, int32_t speed) {/*speed range: -100 100*/
  ussr_call_void_controller_method(env, "setSpeedCentralJoint", "(I)V", speed);
}
int32_t sendMessage(USSREnv *env, uint8_t *message, int32_t messageSize, int32_t connector) {
  jbyteArray array = ussr_charArray2byteArray(env, message, messageSize);
  int32_t result = ussr_call_int_controller_method(env, "sendMessage", "([BII)I", array, messageSize, connector);
  ussr_releaseByteArray(env, array);
  return result;
}
float getCentralJointEncoderValueFloat(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getCentralJointEncoderValueFloat", "()F");
}
int32_t getCentralJointEncoderValue(USSREnv *env) {
  int result = ussr_call_int_controller_method(env, "getCentralJointEncoderValueInt", "()I");
  return result;
}
int32_t isConnected(USSREnv *env, uint8_t connector) {
  int result = ussr_call_int_controller_method(env, "isConnected", "(I)I", connector);
  return result;
}
int32_t isDisconnected(USSREnv *env, uint8_t connector) {
  int result = ussr_call_int_controller_method(env, "isDisconnected", "(I)I", connector);
  return result;
}
void connect(USSREnv *env, uint8_t connector) {
  ussr_call_void_controller_method(env, "connect", "(I)V", connector);
}
void disconnect(USSREnv *env, uint8_t connector) {
  ussr_call_void_controller_method(env, "disconnect", "(I)V", connector);
}
int32_t readProximitySensor(USSREnv *env, uint8_t connector) {
  return ussr_call_int_controller_method(env, "readProximitySensor", "(I)I", connector);
}

float getSouthRotationWmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthRotationW", "(I)F", modNo);
}

float getSouthRotationXmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthRotationX", "(I)F", modNo);
}

float getSouthRotationYmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthRotationY", "(I)F", modNo);
}

float getSouthRotationZmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthRotationZ", "(I)F", modNo);
}

float getSouthTranslationXmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthTranslationX", "(I)F", modNo);
}

float getSouthTranslationYmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthTranslationY", "(I)F", modNo);
}

float getSouthTranslationZmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getSouthTranslationZ", "(I)F", modNo);
}

float getNorthRotationWmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthRotationW", "(I)F", modNo);
}

float getNorthRotationXmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthRotationX", "(I)F", modNo);
}

float getNorthRotationYmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthRotationY", "(I)F", modNo);
}

float getNorthRotationZmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthRotationZ", "(I)F", modNo);
}

float getNorthTranslationXmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthTranslationX", "(I)F", modNo);
}

float getNorthTranslationYmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthTranslationY", "(I)F", modNo);
}

float getNorthTranslationZmodNo(USSREnv *env, int modNo) {
  return ussr_call_float_controller_method(env, "getNorthTranslationZ", "(I)F", modNo);
}

float getSouthRotationW(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthRotationW", "()F");
}

float getSouthRotationX(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthRotationX", "()F");
}

float getSouthRotationY(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthRotationY", "()F");
}

float getSouthRotationZ(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthRotationZ", "()F");
}

float getSouthTranslationX(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthTranslationX", "()F");
}

float getSouthTranslationY(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthTranslationY", "()F");
}

float getSouthTranslationZ(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getSouthTranslationZ", "()F");
}

float getNorthRotationW(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthRotationW", "()F");
}

float getNorthRotationX(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthRotationX", "()F");
}

float getNorthRotationY(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthRotationY", "()F");
}

float getNorthRotationZ(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthRotationZ", "()F");
}

float getNorthTranslationX(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthTranslationX", "()F");
}

float getNorthTranslationY(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthTranslationY", "()F");
}

float getNorthTranslationZ(USSREnv *env) {
  return ussr_call_float_controller_method(env, "getNorthTranslationZ", "()F");
}

/* x -> comp=1   y -> comp=2   z -> comp=3*/
float getGlobalPosition(USSREnv *env, int conn, int comp) {
  return ussr_call_float_controller_method(env, "getGlobalPosition", "(II)F", conn, comp);
}

/* API upcalls/event */
void JNICALL Java_ussr_samples_atron_natives_ATRONNativeTinyOSController_nativeSendDone(JNIEnv *jniENV, jobject self, jint initializationContext, /**/jint error, jint connector) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_SENDDONE__

}
void JNICALL Java_ussr_samples_atron_natives_ATRONNativeTinyOSController_nativeHandleMessage(JNIEnv *jniENV, jobject self, jint initializationContext, jbyteArray message, jint messageSize, jint channel) {
  int moduleId;
  unsigned char buffer[USSR_MAX_MESSAGE_SIZE];
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

  ussr_byteArray2charArray(&env, message, messageSize, buffer);

__AUTO_GENERATED_DISPATCHER_HANDLEMESSAGE__

}

void JNICALL Java_ussr_samples_atron_natives_ATRONNativeTinyOSController_nativeMillisecondElapsed(JNIEnv *jniENV, jobject self, jint initializationContext) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_MILLISECONDELAPSED__

}

void JNICALL Java_ussr_samples_atron_natives_ATRONNativeTinyOSController_native_1stub(JNIEnv *jniENV, jobject self, jint initializationContext) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_NATIVESTUB__

}
