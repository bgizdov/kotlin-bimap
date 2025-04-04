package client


class BiMap<K, V> {
    private val forward = mutableMapOf<K, V>()
    private val reverse = mutableMapOf<V, K>()

   constructor()

    val inverse: BiMap<V, K> by lazy {
        BiMap<V, K>().also { reverseMap ->
            forward.forEach { (k, v) ->
                reverseMap.forward[v] = k
                reverseMap.reverse[k] = v
            }
        }
    }

    operator fun get(key: K): V? = forward[key]

    operator fun set(key: K, value: V): V? {
        val oldValue = forward[key]
        oldValue?.let { reverse.remove(it) }
        forward[key] = value
        reverse[value] = key
        return oldValue
    }

     fun remove(key: K): V? {
        val value = forward.remove(key)
        value?.let { reverse.remove(it) }
        return value
    }

    val keys: Set<K>
        get() = forward.keys

    val values: Set<V>
        get() = forward.values.toSet()
}