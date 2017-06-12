package br.com.igti.android.oglesbasic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maciel on 11/28/14.
 */
public class GLCuboEx {
    private float mVertices[] = {
            1, 1,-1, // ponto 0
            1,-1,-1, // ponto 1
           -1,-1,-1, // ponto 2
           -1, 1,-1, // ponto 3
            1, 1, 1, // ponto 4
            1,-1, 1, // ponto 5
           -1,-1, 1, // ponto 6
           -1, 1, 1, // ponto 7

    };

    private FloatBuffer mVerticesBuffer;

    // Um cubo é composto de 12 triângulos
    private short[] mIndicesVertices = { 3,4,0,  0,4,1,  3,0,1,
                                         3,7,4,  7,6,4,  7,3,6,
                                         3,1,2,  1,6,2,  6,3,2,
                                         1,4,5,  5,6,1,  6,5,4};

    private ShortBuffer mIndicesBuffer;

    public GLCuboEx() {
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
    }

    public void draw (GL10 gl) {
        // Vamos escolher os pontos no sentido horário
        gl.glFrontFace(GL10.GL_CW);
        // Não renderizar a face de trás dos triângulos
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        // Habilita a sessão pra ligar os pontos via vertices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Dá o pointer dos vértices e diz que temos um ponto
        // para cada três coordenadas
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,mVerticesBuffer);
        // Desenha, cobrindo a superfice por triângulos
        gl.glDrawElements(GL10.GL_TRIANGLES,mIndicesVertices.length,GL10.GL_UNSIGNED_SHORT,mIndicesBuffer);
        // fecha os pontos
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Desfaz o cull_face
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
