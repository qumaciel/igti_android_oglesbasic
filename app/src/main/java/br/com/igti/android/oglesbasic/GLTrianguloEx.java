package br.com.igti.android.oglesbasic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maciel on 11/28/14.
 */
public class GLTrianguloEx {
    private float mVertices[] = {
            0f, 1f, // ponto 0
            1f, -1f, // ponto 1
            -1f, -1f // ponto 1
    };

    private FloatBuffer mVerticesBuffer;

    private short[] mIndicesVertices = { 0, 1, 2};

    private ShortBuffer mIndicesBuffer;

    private float mRgba[] = {
            1, 1, 0, .5f, // ponto 0
            .25f, 0, .85f, 1, // ponto 1
            .3f, .7f, .4f, 1 // ponto 1
    };

    private FloatBuffer mColorBuffer;

    public GLTrianguloEx() {
        ByteBuffer verticesByteBuffer = ByteBuffer.allocateDirect(mVertices.length * 4);
        verticesByteBuffer.order(ByteOrder.nativeOrder());
        mVerticesBuffer = verticesByteBuffer.asFloatBuffer();
        mVerticesBuffer.put(mVertices);
        mVerticesBuffer.position(0);

        ByteBuffer indiceByteBuffer = ByteBuffer.allocateDirect(mIndicesVertices.length * 2);
        indiceByteBuffer.order(ByteOrder.nativeOrder());
        mIndicesBuffer = indiceByteBuffer.asShortBuffer();
        mIndicesBuffer.put(mIndicesVertices);
        mIndicesBuffer.position(0);

        ByteBuffer colorByteBuffer = ByteBuffer.allocateDirect(mRgba.length * 4);
        colorByteBuffer.order(ByteOrder.nativeOrder());
        mColorBuffer = colorByteBuffer.asFloatBuffer();
        mColorBuffer.put(mRgba);
        mColorBuffer.position(0);
    }

    public void draw (GL10 gl) {
        // Vamos escolher os pontos no sentido horário
        gl.glFrontFace(GL10.GL_CW);
        // Habilita a sessão pra ligar os pontos via vertices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Habilita para colorir
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        // Dá o pointer dos vértices e diz que temos um ponto
        // para cada duas coordenadas
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,mVerticesBuffer);
        // Dá o pointer dos vértices  para cor e diz que temos um ponto
        // para cada quatro coordenadas
        gl.glColorPointer(4,GL10.GL_FLOAT,0,mColorBuffer);
        // Desenha, cobrindo a superfice por triângulos
        gl.glDrawElements(GL10.GL_TRIANGLES,mIndicesVertices.length,GL10.GL_UNSIGNED_SHORT,mIndicesBuffer);
        // fecha a coloração
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        // fecha os pontos
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
