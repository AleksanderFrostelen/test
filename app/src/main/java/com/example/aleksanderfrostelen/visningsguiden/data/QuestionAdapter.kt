package com.example.aleksanderfrostelen.visningsguiden.data

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aleksanderfrostelen.visningsguiden.R
import kotlinx.android.synthetic.main.question_view.view.*


class QuestionAdapter(private val contents: MutableList<UserQuestions>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun getContents() : List<UserQuestions> {
        return contents
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_view, parent, false) as View

        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as QuestionViewHolder).bind(contents[position])
    }

    inner class QuestionViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(userQuestions: UserQuestions) = with(view) {
            question.text = userQuestions.questionText
            userNotesText.setText(userQuestions.note)
            userNotesText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    contents[adapterPosition].note = p0.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })

            questionCheckbox.isChecked = userQuestions.checked
            questionCheckbox.setOnCheckedChangeListener { _, b ->
                contents[adapterPosition].checked = b
            }
        }
    }

    override fun getItemCount() = contents.size
}

