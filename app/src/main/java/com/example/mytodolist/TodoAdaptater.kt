package com.example.mytodolist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter (
    private val todos: MutableList<Todo>
        ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false)
        )
    }
    private fun toggleTextThrough(rvTodoText: TextView,isChecked : Boolean){
        if(isChecked){
            rvTodoText.paintFlags = rvTodoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            rvTodoText.paintFlags = rvTodoText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.checked
        }
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       val curTodo = todos[position]
       holder.itemView.apply {
           rvTodoTitle.text =curTodo.title
           cbDone.isChecked = curTodo.checked
           toggleTextThrough(rvTodoTitle,curTodo.checked)
           cbDone.setOnCheckedChangeListener { _, isChecked ->
               toggleTextThrough(rvTodoTitle, isChecked)
               curTodo.checked = !curTodo.checked
           }
       }
    }

    override fun getItemCount(): Int {
      return  todos.size
    }
}
