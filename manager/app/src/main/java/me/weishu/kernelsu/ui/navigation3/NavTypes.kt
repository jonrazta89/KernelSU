package me.weishu.kernelsu.ui.navigation3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Simple navigation key interface for type-safe navigation.
 */
interface NavKey {
    val routePattern: String
        get() = this::class.simpleName?.lowercase() ?: "unknown"
}

/**
 * Simple back stack implementation that doesn't depend on miuix.
 */
class NavBackStack<T : NavKey>(
    val initial: T,
) {
    private val _items = mutableStateListOf<T>(initial)
    private val _current = mutableStateOf(initial)

    val items: List<T> get() = _items
    val current: T get() = _current.value
    val size: Int get() = _items.size
    val isNotEmpty: Boolean get() = _items.isNotEmpty()
    val last: T get() = _items.last()
    val lastIndex: Int get() = _items.lastIndex
    val lastOrNull: T? get() = _items.lastOrNull()

    operator fun get(index: Int): T = _items[index]
    operator fun set(index: Int, value: T) {
        _items[index] = value
        if (index == _items.lastIndex) {
            _current.value = value
        }
    }

    fun add(key: T) {
        _items.add(key)
        _current.value = key
    }

    fun addAll(keys: List<T>) {
        _items.addAll(keys)
        _current.value = keys.last()
    }

    fun clear() {
        _items.clear()
    }

    fun removeAt(index: Int) {
        _items.removeAt(index)
        if (index == _items.lastIndex) {
            _current.value = _items.lastOrNull() ?: initial
        }
    }

    fun removeLastOrNull(): T? {
        val removed = _items.removeLastOrNull()
        if (removed != null) {
            _current.value = _items.lastOrNull() ?: initial
        }
        return removed
    }

    operator fun contains(key: T): Boolean = _items.contains(key)
}

/**
 * Creates and remembers a NavBackStack.
 */
@Composable
fun <T : NavKey> rememberNavBackStack(startRoute: T): NavBackStack<T> {
    return remember(startRoute) { NavBackStack(startRoute) }
}
