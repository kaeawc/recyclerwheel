package io.kaeawc.materialwheel

import android.graphics.Camera
import android.view.animation.Transformation
import android.view.animation.Animation
import java.lang.ref.WeakReference

class XAxisRotation constructor(
        var centerX: Float = 0f,
        var centerY: Float = 0f,
        var xAxisDegrees: Float = 0f,
        var reverse: Boolean = false) : Animation() {

    var camera: WeakReference<Camera>? = null

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        camera = WeakReference(Camera())
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val camera = camera?.get() ?: return
        val matrix = t.matrix
        camera.save()
        camera.translate(0.0f, 0.0f, 0f)
        camera.rotateX(xAxisDegrees)
        camera.getMatrix(matrix)
        camera.restore()
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
    }
}
