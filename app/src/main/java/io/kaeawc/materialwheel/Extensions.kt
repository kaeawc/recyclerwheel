package io.kaeawc.materialwheel

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView?.watchForChange(
        before: (CharSequence, Int, Int, Int) -> Unit = {a, b, c, d -> },
        after: (Editable) -> Unit = { a -> },
        during: (CharSequence, Int, Int, Int) -> Unit = {a, b, c, d -> }) {
    this?.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)  { before(s, start, count, after) }
        override fun afterTextChanged(s: Editable) { after(s) }
        override fun onTextChanged(s: CharSequence , start: Int, before: Int, count: Int) { during(s, start, before, count) }
    })
}
