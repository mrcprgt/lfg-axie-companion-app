package com.lfgcompany.lfgaxiecompanionapp.tools.repository

import java.util.HashMap

class RequestParameterHelper {

    private val params = HashMap<String, String>()

    companion object {
        const val KEY_STATUS                = "status"
        const val KEY_UPDATED_AT_MIN        = "updated_at_min"
        const val KEY_UPDATED_AT_MAX        = "updated_at_max"
        const val KEY_CREATED_AT_MIN        = "created_at_min"
        const val KEY_CREATED_AT_MAX        = "created_at_max"
        const val KEY_AFTER_CREATED_AT      = "after_created_at"
        const val KEY_AFTER_UPDATED_AT      = "after_updated_at"
        const val KEY_PAGE                  = "page"
        const val KEY_SINCE_ID              = "since_id"
        const val KEY_SORT_BY_ID            = "sort_by_id"
        const val KEY_SORT_BY_CREATED_AT    = "sort_by_created_at"
        const val KEY_SORT_BY_UPDATED_AT    = "sort_by_updated_at"
        const val KEY_KEYWORD               = "keyword"
        const val MAX_ITEM_PER_PAGE         = 20
    }

    enum class SortOrderBy(val str : String) {
        DESC("desc"), ASC("asc")
    }

    fun status(status : String) : RequestParameterHelper {
        params[KEY_STATUS] = status
        return this
    }

    fun sinceId(id : Int) :RequestParameterHelper {
        params[KEY_SINCE_ID] = id.toString()
        return this
    }

    fun updatedAtMin(updatedAtMin: String): RequestParameterHelper {
        params[KEY_UPDATED_AT_MIN] = updatedAtMin
        return this
    }

    fun updatedAtMax(updatedAtMax: String): RequestParameterHelper {
        params[KEY_UPDATED_AT_MAX] = updatedAtMax
        return this
    }

    fun createdAtMin(createdAtMin: String): RequestParameterHelper {
        params[KEY_CREATED_AT_MIN] = createdAtMin
        return this
    }

    fun createdAtMax(createdAtMax: String): RequestParameterHelper {
        params[KEY_CREATED_AT_MAX] = createdAtMax
        return this
    }

    fun afterCreatedAt(afterCreatedAt: String): RequestParameterHelper {
        params[KEY_AFTER_CREATED_AT] = afterCreatedAt
        return this
    }

    fun afterUpdatedAt(afterUpdatedAt: String): RequestParameterHelper {
        params[KEY_AFTER_UPDATED_AT] = afterUpdatedAt
        return this
    }

    fun page(page: Int): RequestParameterHelper {
        params[KEY_PAGE] = page.toString()
        return this
    }

    fun addParam(key : String, value : String) : RequestParameterHelper {
        params[key] = value
        return this
    }

    fun sortById(sortOrderBy : SortOrderBy) : RequestParameterHelper {
        params[KEY_SORT_BY_ID] = sortOrderBy.str
        return this
    }

    fun sortByCreatedAt(sortOrderBy : SortOrderBy) : RequestParameterHelper {
        params[KEY_SORT_BY_CREATED_AT] = sortOrderBy.str
        return this
    }

    fun sortByUpdatedAt(sortOrderBy : SortOrderBy) : RequestParameterHelper {
        params[KEY_SORT_BY_UPDATED_AT] = sortOrderBy.str
        return this
    }

    fun keyword(keyword : String) : RequestParameterHelper {
        params[KEY_KEYWORD] = keyword
        return this;
    }

    fun removeParam(key : String) : RequestParameterHelper {
        params.remove(key).takeIf { params.containsKey(key) }
        return this
    }

    fun removeStatus() : RequestParameterHelper {
        params.remove(KEY_STATUS).takeIf { params.containsKey(KEY_STATUS) }
        return this
    }

    fun removeUpdatedAtMin() : RequestParameterHelper {
        params.remove(KEY_UPDATED_AT_MIN).takeIf { params.containsKey(KEY_UPDATED_AT_MIN) }
        return this
    }

    fun removeUpdatedAtMax() : RequestParameterHelper {
        params.remove(KEY_UPDATED_AT_MAX).takeIf { params.containsKey(KEY_UPDATED_AT_MAX) }
        return this
    }

    fun removeCreatedAtMin() : RequestParameterHelper {
        params.remove(KEY_CREATED_AT_MIN).takeIf { params.containsKey(KEY_CREATED_AT_MIN) }
        return this
    }

    fun removeCreatedAtMax() : RequestParameterHelper {
        params.remove(KEY_CREATED_AT_MAX).takeIf { params.containsKey(KEY_CREATED_AT_MAX) }
        return this
    }

    fun removeAfterCreatedAt() : RequestParameterHelper {
        params.remove(KEY_AFTER_CREATED_AT).takeIf { params.containsKey(KEY_AFTER_CREATED_AT) }
        return this
    }

    fun removeAfterUpdatedAt() : RequestParameterHelper {
        params.remove(KEY_AFTER_UPDATED_AT).takeIf { params.containsKey(KEY_AFTER_UPDATED_AT) }
        return this
    }

    fun removeSortById() : RequestParameterHelper {
        params.remove(KEY_SORT_BY_ID).takeIf { params.containsKey(KEY_SORT_BY_ID) }
        return this
    }

    fun removeSortByCreatedAt() : RequestParameterHelper {
        params.remove(KEY_SORT_BY_CREATED_AT).takeIf { params.containsKey(KEY_SORT_BY_CREATED_AT) }
        return this
    }

    fun removeSortByUpdatedAt() : RequestParameterHelper {
        params.remove(KEY_SORT_BY_UPDATED_AT).takeIf { params.containsKey(KEY_SORT_BY_UPDATED_AT) }
        return this
    }

    fun build(): HashMap<String, String> {
        return params
    }

}
