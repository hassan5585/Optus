package tech.mujtaba.optus.view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tech.mujtaba.optus.R

class ItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val margin = context.resources.getDimensionPixelSize(R.dimen.padding_standard)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = margin
            }
            left = margin
            right = margin
            bottom = margin
        }
    }
}