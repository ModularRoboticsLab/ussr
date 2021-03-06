#include <odin_tinyos.h>
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
int getRandomNumber(USSREnv *env) {
  int random = ussr_call_int_controller_method(env, "getRandomNumber", "()I");
  return random;
}
int moduleType(USSREnv *env) {
  int type = ussr_call_int_controller_method(env, "moduleType", "()I");
  return type;
}
void setActuatorSpeed(USSREnv* env, int8_t value) {
  ussr_call_void_controller_method(env, "setActuatorSpeed", "(I)V", value);  
}
int getActuatorPosition(USSREnv* env) {
  return ussr_call_int_controller_method(env, "getActuatorPosition", "()I");
}
/* //this does not really work ... ! */
/* void setPositionCentralJoint(USSREnv *env, int32_t position) { */
/*   ussr_call_void_controller_method(env, "setPositionCentralJoint", "(I)V", position); */
/* } */


/* void setSpeedCentralJoint(USSREnv *env, int32_t speed) {/\*speed range: -100 100*\/ */
/*   ussr_call_void_controller_method(env, "setSpeedCentralJoint", "(I)V", speed); */
/* } */
int32_t sendMessage(USSREnv *env, uint8_t *message, int32_t messageSize, int32_t connector) {
  jbyteArray array = ussr_charArray2byteArray(env, message, messageSize);
  int32_t result = ussr_call_int_controller_method(env, "sendMessage", "([BII)I", array, messageSize, connector);
  ussr_releaseByteArray(env, array);
  return result;
}

/* API upcalls/event */
void JNICALL Java_ussr_samples_odin_natives_OdinNativeTinyOSController_nativeSendDone(JNIEnv *jniENV, jobject self, jint initializationContext, /**/jint error, jint connector) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_SENDDONE__

}
void JNICALL Java_ussr_samples_odin_natives_OdinNativeTinyOSController_nativeHandleMessage(JNIEnv *jniENV, jobject self, jint initializationContext, jbyteArray message, jint messageSize, jint channel) {
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

void JNICALL Java_ussr_samples_odin_natives_OdinNativeTinyOSController_nativeMillisecondElapsed(JNIEnv *jniENV, jobject self, jint initializationContext) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_MILLISECONDELAPSED__

}

void JNICALL Java_ussr_samples_odin_natives_OdinNativeTinyOSController_native_1stub(JNIEnv *jniENV, jobject self, jint initializationContext) {
  int moduleId;
  USSREnv env;
  env.jnienv = jniENV;
  env.controller = self;
  env.context = initializationContext;

  moduleId = ussr_call_int_controller_method(&env, "getRole", "()I");/* the old "role" */

__AUTO_GENERATED_DISPATCHER_NATIVESTUB__

}
