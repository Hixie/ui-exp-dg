package com.example.hotreloadtest

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.*
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine

actual fun connect(callback: MessageCallback) {
  GlobalScope.launch {
    HttpClient {
      install(WebSockets)
    }.wss(
      host = "websocketstest.com",
      path = "/service"
    ) {
      send(Frame.Text("timer"))
      for (frame in incoming)
        callback((frame as Frame.Text).readText())
    }
  }
}

//class MyContinuation(override val context: CoroutineContext) : Continuation<Unit> {
//  override fun resumeWith(result: Result<Unit>) {}
//}
//
//object MainLoopDispatcher: CoroutineDispatcher() {
//  override fun dispatch(context: CoroutineContext, block: Runnable) {
//    NSRunLoop.mainRunLoop().performBlock {
//      block.run()
//    }
//  }
//}
