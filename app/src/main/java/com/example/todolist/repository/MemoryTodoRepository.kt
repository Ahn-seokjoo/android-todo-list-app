package com.example.todolist.repository

import com.example.todolist.data.Todo
import java.util.concurrent.atomic.AtomicInteger

class MemoryTodoRepository : Repository {
    var id = AtomicInteger(0)

    private val _todoList = mutableListOf<Todo>()//비어있는 리스트로 일단 초기화

    override suspend fun getAll(): List<Todo> {
        return _todoList.sortedBy { it.time }.toMutableList()
    }

    // 추가
    override suspend fun addTodo(todo: Todo) {
        todo.id = id.getAndIncrement()
        _todoList.add(todo)
//        Log.d(TAG, "addTodo: 추가 하기")
    }

    //삭제
    override suspend fun removeTodo(todo: Todo) {
        _todoList.remove(todo)
//        Log.d(TAG, "removeTodo: 삭제 하기")
    }

    //수정
    override suspend fun updateTodo(todo: Todo) {
        val changeData = _todoList.map {
            //매핑해줌
            if (it.id == todo.id) {
                todo
            } else {
                it
            }
        }.sortedBy { it.time }
        _todoList.apply {
            clear()
            addAll(changeData)
        }
//        Log.d(TAG, "updateTodo: 수정 하기")
    }

    override suspend fun updateTodoList(todo: Todo) {
        _todoList.clear()
        _todoList.add(todo)
    }
}