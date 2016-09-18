package br.com.levisaturnino.cadastroartigos;

import java.io.Serializable;

/**
 * Created by saturnino on 17/09/16.
 */
public class Artigo implements Serializable {

    private static final long serialVersionUID = 1633833011084400384L;
    int id;
    String revista;
    String nome;
    String edicao;
    int status;
    int pago;
}
