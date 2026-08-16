package com.example.voiceassistant

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val container = findViewById<LinearLayout>(R.id.container)
        container.removeAllViews()

        val expressions = ExpressionMapper.allExpressions()
        for (expr in expressions) {
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(0, 8, 0, 8)
                layoutParams = params
                gravity = Gravity.CENTER_VERTICAL
            }

            val label = TextView(this).apply {
                text = expr
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val editBtn = Button(this).apply {
                text = "Edit"
                setOnClickListener {
                    showEditDialog(expr)
                }
            }

            row.addView(label)
            row.addView(editBtn)
            container.addView(row)
        }

        // Add a Reset button
        val reset = Button(this).apply {
            text = "Reset to defaults"
            setOnClickListener { resetDefaults() }
        }
        container.addView(reset)
    }

    private fun showEditDialog(expression: String) {
        val current = ExpressionMapper.getPhraseFor(expression, this) ?: ""
        val input = EditText(this).apply { setText(current) }

        AlertDialog.Builder(this)
            .setTitle("Edit phrase for $expression")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newPhrase = input.text.toString().trim()
                if (newPhrase.isEmpty()) ExpressionMapper.setPhraseFor(expression, null, this)
                else ExpressionMapper.setPhraseFor(expression, newPhrase, this)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun resetDefaults() {
        for (expr in ExpressionMapper.allExpressions()) {
            ExpressionMapper.setPhraseFor(expr, null, this)
        }
    }
}
