package br.com.igti.android.oglesbasic;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maciel on 11/28/14.
 */
public class GLRendererEx implements GLSurfaceView.Renderer {

//    private GLTrianguloEx mTrianguloEx;
    private GLCuboEx mCuboEx;

    public GLRendererEx() {
//        mTrianguloEx = new GLTrianguloEx();
        mCuboEx = new GLCuboEx();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
        gl.glClearColor(.5f,.8f,.5f,1);
        gl.glClearDepthf(1f);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        float ratio = (float) width/height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio,ratio,-1,1,1,25);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0,0,-4,0,0,0,0,2,0);

        long time = SystemClock.uptimeMillis() % 4000L;
        float angle = .090f * ((int)time);

        gl.glRotatef(angle,1,0,1);


//        mTrianguloEx.draw(gl);
        mCuboEx.draw(gl);
    }
}
