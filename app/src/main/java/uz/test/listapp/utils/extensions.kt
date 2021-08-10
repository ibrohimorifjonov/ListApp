package uz.test.listapp.utils


import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_SHORT) {
    val snackBar = Snackbar.make(requireView(), snackbarText, timeLength)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), android.R.color.black))
        .setActionTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
    val displayPixel = (86 * requireContext().resources.displayMetrics.density)
    val snackBarView=snackBar.view
    snackBarView.translationY = -(displayPixel)
    snackBar.show()
}

class SpacesItemDecoration(
    private val space: Int,
    private val vertical: Boolean = true,
    private val span: Int = 2
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (vertical) {

            val itemposition = parent.getChildLayoutPosition(view)
            if (span > 1) {
                val column = itemposition % span

                outRect.left = space - column * space / span
                outRect.right = (column + 1) * space / span
                outRect.bottom = space

                if (itemposition < span) {
                    outRect.top = space
                } else {
                    outRect.top = 0
                }
            } else {
                outRect.left = space
                outRect.right = space
                outRect.bottom = space - space / 4

                if (itemposition == 0)
                    outRect.top = space
                else {
                    outRect.top = 0
                }
            }
        } else {
            val itemposition = parent.getChildLayoutPosition(view)

            outRect.top = 0
            outRect.bottom = 2
            outRect.right = space / 2

            if (itemposition == 0) {
                outRect.left = space
            } else {
                outRect.left = 0
            }
        }

    }
}

fun Fragment.toDp(px: Int): Int {
    return ((requireContext().resources.displayMetrics.density * px) + 0.5f).toInt()
}