package com.example.ass5_room.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class StandardDispatcher() : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val io: CoroutineDispatcher
        get() = Dispatchers.Default

    override val undefined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}