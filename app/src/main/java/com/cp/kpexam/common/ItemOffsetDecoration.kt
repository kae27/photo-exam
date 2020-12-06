package com.cp.kpexam.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

private const val FIRST_CHILD_INDEX = 0

class ItemOffsetDecoration(private val leftOffset: Int,
                           private val topOffset: Int,
                           private val rightOffset: Int,
                           private val bottomOffset: Int,
                           private val horizontalOffset: Int,
                           private val verticalOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context,
                @DimenRes leftItemOffsetId: Int,
                @DimenRes topItemOffsetId: Int,
                @DimenRes rightItemOffsetId: Int,
                @DimenRes bottomItemOffsetId: Int,
                @DimenRes horizontalItemOffsetId: Int,
                @DimenRes verticalItemOffsetId: Int) : this(context.resources.getDimensionPixelSize(leftItemOffsetId),
        context.resources.getDimensionPixelSize(topItemOffsetId),
        context.resources.getDimensionPixelSize(rightItemOffsetId),
        context.resources.getDimensionPixelSize(bottomItemOffsetId),
        context.resources.getDimensionPixelSize(horizontalItemOffsetId),
        context.resources.getDimensionPixelSize(verticalItemOffsetId))

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            left = if (parent.getChildAdapterPosition(view) == FIRST_CHILD_INDEX) {
                leftOffset
            } else {
                horizontalOffset
            }

            top = if (parent.getChildAdapterPosition(view) == FIRST_CHILD_INDEX) {
                topOffset
            } else {
                verticalOffset
            }

            parent.adapter?.let { adapter ->
                right = if (parent.getChildAdapterPosition(view) == (adapter.itemCount - 1)) {
                    rightOffset
                } else {
                    horizontalOffset
                }

                bottom = if (parent.getChildAdapterPosition(view) == (adapter.itemCount - 1)) {
                    bottomOffset
                } else {
                    verticalOffset
                }
            }
        }
    }
}