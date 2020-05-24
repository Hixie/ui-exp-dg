package com.example.hotreloadtest

typealias MessageCallback = (String) -> Unit

fun start(update: MessageCallback) {
  MyClass().start(update)
}

class MyClass {
  private var count: Int = 0
  private var uiUpdater: MessageCallback? = null

  fun start(updater: MessageCallback) {
    uiUpdater = updater
    uiUpdater!!("Connecting...")
    connect(::messageHandler)
  }

  private fun messageHandler(message: String): Unit {
    count += 1
    uiUpdater!!("$count: $message")
  }
}

expect fun connect(callback: MessageCallback): Unit
