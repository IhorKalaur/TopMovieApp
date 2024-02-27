package ihor.kalaur.topmovieapp.util

enum class PaginationState {
    REQUEST_INACTIVE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
    EMPTY,
}