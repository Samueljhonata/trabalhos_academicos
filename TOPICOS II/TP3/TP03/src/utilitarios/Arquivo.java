/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.io.*;
import java.util.*;

/**
 *
 * @author Samuel
 */
public class Arquivo {

    public ArrayList<String> lerArquivo(String nomeArq) throws Exception {
        FileReader fr = null;
        BufferedReader bf = null;
        ArrayList<String> texto = new ArrayList();

        try {
            fr = new FileReader(nomeArq);
            bf = new BufferedReader(fr);
            String linha = "";

            while ((linha = bf.readLine()) != null) {
                texto.add(linha);
            }

            bf.close();
            fr.close();
            return texto;
        } catch (Exception e) {
            throw new Exception();

        }

    }

    public boolean escreveNoArquivo(String nomeArquivo, ArrayList<String> texto, boolean apagaAnterior) {
        FileWriter fw = null;
        String a;

        try {
            if (apagaAnterior) {
                fw = new FileWriter(nomeArquivo, false);
            } else {
                fw = new FileWriter(nomeArquivo, true);
            }

            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < texto.size(); i++) {
                a = texto.get(i).replaceAll(" ", "");
                bw.write(a + "\r\n");
            }

            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean limpaArquivo(String nomeArquivo) {
        try {
            FileWriter fw = new FileWriter(nomeArquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
