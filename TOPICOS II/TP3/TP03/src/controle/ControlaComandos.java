package controle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ClasseSeguranca;
import modelo.Processo;
import modelo.TModo;
import modelo.Usuario;

public class ControlaComandos {

    private ControlaProcesso controlaProcesso;
    private ControlaComandosSistema controlaComandosSistema;

    public ControlaComandos() {
        controlaProcesso = new ControlaProcesso();
        controlaComandosSistema = new ControlaComandosSistema();
    }

    private boolean executaUpdate(String sql, Usuario usuario, TModo modo) {
        String numProcesso = null, nomeAutor = null, nomeReu = null, descricaoAuto = null, sentenca = null;
        try {
            String quebra = sql.split("SET")[1].split("WHERE")[0];
            if (quebra.contains("numProcesso")) {
                numProcesso = quebra.split("numProcesso")[1].split("'")[1];
            }

            if (quebra.contains("nomeReu")) {
                nomeReu = quebra.split("nomeReu")[1].split("'")[1];
            }

            if (quebra.contains("nomeAutor")) {
                nomeAutor = quebra.split("nomeAutor")[1].split("'")[1];
            }

            if (quebra.contains("descricaoAuto")) {
                descricaoAuto = quebra.split("descricaoAuto")[1].split("'")[1];
            }

            if (quebra.contains("sentenca")) {
                sentenca = quebra.split("sentenca")[1].split("'")[1];
            }

            //recupera todos os processos que serão alterados
            String sqlAux = "SELECT * FROM processo WHERE " + montaWhereUPDATE(sql, usuario.getClasseSeguranca()) + " ORDER BY numProcesso ASC, TC DESC"; //pesquisa todas as tuplas que entram nas condições
            ArrayList<Processo> a = controlaProcesso.pesquisaProcessosSimples(sqlAux, modo);
            //ordena a lista agrupando numProcessos e ordenando de forma decrescente pelo valor de TC
            if (a != null) {
                int tam = a.size();
                String ultimoProcesso = "";
                for (int i = 0; i < tam; i++) {
                    if (a.get(i).getNumProcesso().equals(ultimoProcesso)) {
                        continue;
                    }
                    ultimoProcesso = a.get(i).getNumProcesso();
                    Processo processo = a.get(i);
                    Processo processoAtualizar = null;
                    try {
                        processoAtualizar = processo.clone();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(ControlaComandos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (numProcesso != null) {
                        processoAtualizar.setNumProcesso(numProcesso);
                        processoAtualizar.setC_numProcesso(usuario.getClasseSeguranca());
                    }
                    if (nomeAutor != null) {
                        processoAtualizar.setNomeAutor(nomeAutor);
                        processoAtualizar.setC_nomeAutor(usuario.getClasseSeguranca());
                    }
                    if (nomeReu != null) {
                        processoAtualizar.setNomeReu(nomeReu);
                        processoAtualizar.setC_nomeReu(usuario.getClasseSeguranca());
                    }
                    if (descricaoAuto != null) {
                        processoAtualizar.setDescricaoAuto(descricaoAuto);
                        processoAtualizar.setC_descricaoAuto(usuario.getClasseSeguranca());
                    }
                    if (sentenca != null) {
                        processoAtualizar.setSentenca(sentenca);
                        processoAtualizar.setC_sentenca(usuario.getClasseSeguranca());
                    }

                    if (processo.getTC().getNum() != usuario.getClasseSeguranca().getNum()) {//não existe tupla para aquele nivel
                        //insere uma tupla para o nivel do usuario, a partir da tupla de maior nível, porem menor que do usuario
                        String sqlInsert = "INSERT INTO processo VALUES ('" + processoAtualizar.getNumProcesso() + "', " + processoAtualizar.getC_numProcesso().getNum()
                                + ", '" + processoAtualizar.getNomeAutor() + "', " + processoAtualizar.getC_nomeAutor().getNum()
                                + ", '" + processoAtualizar.getNomeReu() + "', " + processoAtualizar.getC_nomeReu().getNum()
                                + ", '" + processoAtualizar.getDescricaoAuto() + "', " + processoAtualizar.getC_descricaoAuto().getNum()
                                + ", '" + processoAtualizar.getSentenca() + "" + "', "
                                + processoAtualizar.getC_sentenca().getNum() + "," + processoAtualizar.getTC().getNum() + ")";
                        controlaProcesso.insereProcesso(sqlInsert, modo);

                        ultimoProcesso = a.get(i).getNumProcesso();

                    } else {
                        //da update
                        controlaProcesso.atualizaProcesso(processoAtualizar, modo);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            //return false;
        }
        return true;
    }

    public void recebeComandos(String sql, Usuario usuario, TModo modo) {
        String comando = sql.split(" ")[0].toUpperCase();
        //manipulação da tabela processo
        if (comando.equalsIgnoreCase("INSERT")) {
            sql = montaInsercao(sql, usuario.getClasseSeguranca());
            if (!controlaProcesso.insereProcesso(sql, modo)) {
                System.out.println("-- ERRO AO EXECUTAR COMANDO!");
            }
            return;
        }

        if (comando.equalsIgnoreCase("SELECT")) {
            sql = montaWherePesquisa(sql, usuario.getClasseSeguranca());
            sql = montaSelecao(sql);

            ArrayList<Processo> a = controlaProcesso.pesquisaProcessos(sql, usuario, modo);
            if (a != null) {
                for (Processo processo : a) {
                    System.out.println(processo.mostraProcesso(modo));
                }
            }
            return;
        }

        if (comando.equalsIgnoreCase("UPDATE")) {
            this.executaUpdate(sql, usuario, modo);
            return;
        }

        if (comando.equalsIgnoreCase("DELETE")) {
            String select = sql.replaceAll("DELETE", "SELECT * ");
            ArrayList<Processo> a = controlaProcesso.pesquisaProcessosSimples(montaWherePesquisa(select, usuario.getClasseSeguranca()), modo); //todos os processos que enquadram
            if (a != null) {
                for (Processo processo : a) {
                    if (processo.getTC().getNum() <= usuario.getClasseSeguranca().getNum()) { //TC da tupla é menor ou igual à classe do usuário
                        //exclui
                        controlaProcesso.excluiProcesso(processo.getNumProcesso(), processo.getTC(), modo);
                    } else {
                        //atualiza os campos necessários
                        if (processo.getC_numProcesso().getNum() <= usuario.getClasseSeguranca().getNum()) {
                            processo.setC_numProcesso(ClasseSeguranca.retorna(usuario.getClasseSeguranca().getNum() + 1));
                        }
                        if (processo.getC_nomeReu().getNum() <= usuario.getClasseSeguranca().getNum()) {
                            processo.setC_nomeReu(ClasseSeguranca.retorna(usuario.getClasseSeguranca().getNum() + 1));
                        }
                        if (processo.getC_nomeAutor().getNum() <= usuario.getClasseSeguranca().getNum()) {
                            processo.setC_nomeAutor(ClasseSeguranca.retorna(usuario.getClasseSeguranca().getNum() + 1));
                        }
                        if (processo.getC_descricaoAuto().getNum() <= usuario.getClasseSeguranca().getNum()) {
                            processo.setC_descricaoAuto(ClasseSeguranca.retorna(usuario.getClasseSeguranca().getNum() + 1));
                        }
                        if (processo.getC_sentenca().getNum() <= usuario.getClasseSeguranca().getNum()) {
                            processo.setC_sentenca(ClasseSeguranca.retorna(usuario.getClasseSeguranca().getNum() + 1));
                        }

                        //atualiza no banco
                        controlaProcesso.atualizaProcesso(processo, modo);
                    }
                }
            }
            return;
        }

        //controle do sistema de usuarios
        if (comando.equalsIgnoreCase("CREATE")
                || comando.equalsIgnoreCase("DROP")
                || comando.equalsIgnoreCase("SET")
                || comando.equalsIgnoreCase("RENAME")) {
            if (!controlaComandosSistema.recebeComando(sql, usuario, modo)) {
                System.out.println("-- ERRO AO EXECUTAR COMANDO!");
            }
            return;
        }

        System.out.println("-- COMANDO NAO RECONHECIDO!");
    }

    //monta a cláusula select da pesquisa inserindo os atributos de segurança
    private String montaSelecao(String pesquisa) {
        String retorno = pesquisa;
        String select;
        String quebra[];
        if (pesquisa.contains("*")) {
            return pesquisa;
        }
        quebra = pesquisa.split("FROM");
        select = quebra[0];
        select = select.replaceAll("numProcesso", " C_numProcesso, " + "numProcesso");
        select = select.replaceAll("nomeAutor", " C_nomeAutor, " + "nomeAutor");
        select = select.replaceAll("nomeReu", " C_nomeReu, " + "nomeReu");
        select = select.replaceAll("descricaoAuto", " C_descricaoAuto, " + "descricaoAuto");
        select = select.replaceAll("sentenca", " C_sentenca, " + "sentenca");

        retorno = select + ", TC FROM " + quebra[1];
        return retorno;

    }

    //monta a cláusula where da pesquisa inserindo os atributos de segurança, de acordo com a classe do usuario
    private String montaWherePesquisa(String pesquisa, ClasseSeguranca classeSeguranca) {
        String retorno = pesquisa;
        String[] quebra;
        String where;
        quebra = pesquisa.split("WHERE");

        if (quebra.length > 1) {
            where = quebra[1];
            where = where.replaceAll("numProcesso", " C_numProcesso <= " + classeSeguranca.getNum() + " AND numProcesso");
            where = where.replaceAll("nomeAutor", " C_nomeAutor <= " + classeSeguranca.getNum() + " AND nomeAutor");
            where = where.replaceAll("nomeReu", " C_nomeReu <= " + classeSeguranca.getNum() + " AND nomeReu");
            where = where.replaceAll("descricaoAuto", " C_descricaoAuto <= " + classeSeguranca.getNum() + " AND descricaoAuto");
            where = where.replaceAll("sentenca", " C_sentenca <= " + classeSeguranca.getNum() + " AND sentenca");

            retorno = quebra[0] + " WHERE " + where;
        } else {
            retorno = pesquisa;
        }

        return retorno;
    }

    //monta a cláusula where da pesquisa inserindo os atributos de segurança, de acordo com a classe do usuario
    private String montaWhereUPDATE(String pesquisa, ClasseSeguranca classeSeguranca) {
        //pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
        String[] quebra;
        String where = "";
        quebra = pesquisa.split("WHERE");

        if (quebra.length > 1) {
            where = quebra[1] + " AND TC <= " + classeSeguranca.getNum();
        } else {
            where = "TC <= " + classeSeguranca.getNum();
        }

        return where;
    }

    private String montaInsercao(String sql, ClasseSeguranca classeSeguranca) {
        sql = sql.replaceAll("\\);", "").replaceAll("\\)", "");
        String[] a = sql.split("\\(");
        String[] quebra = a[1].split(",");
        String retorno = "INSERT INTO processo VALUES(";
        retorno = retorno + quebra[0] + ", " + classeSeguranca.getNum() + ", "; //numProcesso
        retorno = retorno + quebra[1] + ", " + classeSeguranca.getNum() + ", "; //nomeAutor
        retorno = retorno + quebra[2] + ", " + classeSeguranca.getNum() + ", "; //nomeReu
        retorno = retorno + quebra[3] + ", " + classeSeguranca.getNum() + ", "; //descricaoAuto
        retorno = retorno + quebra[4] + ", " + classeSeguranca.getNum() + ", "; //sentenca
        retorno = retorno + " " + classeSeguranca.getNum() + ");";

        return retorno;
    }

}
