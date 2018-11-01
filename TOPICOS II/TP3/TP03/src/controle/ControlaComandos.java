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

    public void recebeComandos(String sql, Usuario usuario, TModo modo) {
        String comando = sql.split(" ")[0].toUpperCase();
        //manipulçação da tabela processo
        if (comando.equalsIgnoreCase("INSERT")) {
            sql = montaInsercao(sql, usuario.getClasseSeguranca());
            if (!controlaProcesso.insereProcesso(sql)) {
                System.out.println("-- ERRO AO EXECUTAR COMANDO!");
            }
            return;
        }

        if (comando.equalsIgnoreCase("SELECT")) {
            sql = montaWhere(sql, usuario.getClasseSeguranca());
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
            String numProcesso = null, nomeAutor = null, nomeReu = null, descricaoAuto = null, sentenca = null;
            String quebra = sql.split("SET")[1].split("WHERE")[0];
            if (quebra.contains("numProcesso")) {
                numProcesso = quebra.split("numProcesso")[1].split("'")[1];
                System.out.println(numProcesso);
            }

            if (quebra.contains("nomeReu")) {
                nomeReu = quebra.split("nomeReu")[1].split("'")[1];
                System.out.println(nomeReu);
            }

            if (quebra.contains("nomeAutor")) {
                nomeAutor = quebra.split("nomeAutor")[1].split("'")[1];
                System.out.println(nomeAutor);
            }

            if (quebra.contains("descricaoAuto")) {
                descricaoAuto = quebra.split("descricaoAuto")[1].split("'")[1];
                System.out.println(descricaoAuto);
            }

            if (quebra.contains("sentenca")) {
                sentenca = quebra.split("sentenca")[1].split("'")[1];
                System.out.println(sentenca);
            }

            //recupera todos os processos que serão alterados
            String sqlAux = "SELECT * FROM processo WHERE " + montaWhereUPDATE(sql, usuario.getClasseSeguranca()) + " ORDER BY numProcesso ASC, TC DESC"; //pesquisa todas as tuplas que entram nas condições
            ArrayList<Processo> a = controlaProcesso.pesquisaProcessosSimples(sqlAux, usuario);
            //ordena a lista agrupando numProcessos e ordenando de forma decrescente pelo valor de TC
            if (a != null) {
                System.out.println("ENTROU nO IF");
                int tam = a.size();
                String ultimoProcesso = "";
                for (int i = 0; i < tam; i++) {
                    System.out.println(a.get(i).getNumProcesso()+ " =?= " + ultimoProcesso);
                    if (a.get(i).getNumProcesso().equals(ultimoProcesso)) {
                        continue;
                    }
                    ultimoProcesso = a.get(i).getNumProcesso();
                    System.out.println("ENTROU NO FOR");
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
                    System.out.println(processo.getTC() + " =?= " + usuario.getClasseSeguranca());
                    
                    if (processo.getTC().getNum() != usuario.getClasseSeguranca().getNum()) {//não existe tupla para aquele nivel
                        //insere uma tupla para o nivel do usuario, a partir da tupla de maior nível, porem menor que do usuario
                        //da insert

                        String sqlInsert = "INSERT INTO processo VALUES ('" + processoAtualizar.getNumProcesso() + "', " + processoAtualizar.getC_numProcesso().getNum()
                                + ", '" + processoAtualizar.getNomeAutor() + "', " + processoAtualizar.getC_nomeAutor().getNum()
                                + ", '" + processoAtualizar.getNomeReu() + "', " + processoAtualizar.getC_nomeReu().getNum()
                                + ", '" + processoAtualizar.getDescricaoAuto() + "', " + processoAtualizar.getC_descricaoAuto().getNum()
                                + ", '" + processoAtualizar.getSentenca() + "" + "', "
                                + processoAtualizar.getC_sentenca().getNum() + "," + processoAtualizar.getTC().getNum() + ")";
                        System.out.println(sqlInsert);
                        controlaProcesso.insereProcesso(sqlInsert);

                        ultimoProcesso = a.get(i).getNumProcesso();
                        /*try {
                            while (i <= a.size() || a.get(i).getNumProcesso().equals(processo.getNumProcesso())) {//remove da lista todos os processos que têm o mesmo numProcesso
                                System.out.println("REMOVE " + a.get(i).getNumProcesso() + " " + a.get(i).getTC());
                                a.remove(i);
                                tam--;
                            }
                        } catch (Exception e) {

                        }*/
                        //System.out.println(processo.mostraProcesso());
                    }
                    //modifica os atributos e altera a classe de segurança de cada um que foi modificado
                    //da update
                    controlaProcesso.atualizaProcesso(processoAtualizar);
                }

                /*                if (!controlaProcesso.atualizaProcesso(sql)) {
                    System.out.println("-- ERRO AO EXECUTAR COMANDO!");
                }*/
                return;
            }
            
        }
System.out.println("-----"+comando);
            if (comando.equalsIgnoreCase("DELETE")) {
                System.out.println("entrou");
                if(!controlaProcesso.excluiPocesso(montaExclusao(sql, usuario.getClasseSeguranca()))){
                    System.out.println("-- ERRO AO EXECUTAR COMANDO!");
                }else{
                    System.out.println("SUCESSO!");
                }
                System.out.println("saiu");
                return;
            }

            //controle do sistema de usuarios
            if (comando.equalsIgnoreCase("CREATE")
                    || comando.equalsIgnoreCase("DROP")
                    || comando.equalsIgnoreCase("SET")
                    || comando.equalsIgnoreCase("RENAME")) {
                if (!controlaComandosSistema.recebeComando(sql, usuario)) {
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

        retorno = select + " FROM " + quebra[1];
        return retorno;

    }

    private String montaExclusao(String sql, ClasseSeguranca classeSeguranca) {
        if (sql.toUpperCase().contains("WHERE")) {
            sql += " AND TC <= " + classeSeguranca.getNum();
        }else{
            sql += " WHERE TC <= " + classeSeguranca.getNum();
        }

        System.out.println("----- " + sql);
        return sql;
    }

    //monta a cláusula where da pesquisa inserindo os atributos de segurança, de acordo com a classe do usuario
    private String montaWhere(String pesquisa, ClasseSeguranca classeSeguranca) {
        //pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
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
            //System.out.println(where);

            retorno = quebra[0] + " WHERE " + where;
        }

        return retorno;
    }

    //monta a cláusula where da pesquisa inserindo os atributos de segurança, de acordo com a classe do usuario
    private String montaWhereUPDATE(String pesquisa, ClasseSeguranca classeSeguranca) {
        //pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
        String retorno = pesquisa;
        String[] quebra;
        String where = "";
        quebra = pesquisa.split("WHERE");

        if (quebra.length > 1) {
            where = quebra[1] + " AND TC <= " + classeSeguranca.getNum();
            /*where = where.replaceAll("numProcesso", " C_numProcesso <= "+ classeSeguranca.getNum() +" AND numProcesso");
            where = where.replaceAll("nomeAutor", " C_nomeAutor <= "+ classeSeguranca.getNum() +" AND nomeAutor");
            where = where.replaceAll("nomeReu", " C_nomeReu <= "+ classeSeguranca.getNum() +" AND nomeReu");
            where = where.replaceAll("descricaoAuto", " C_descricaoAuto <= "+ classeSeguranca.getNum() +" AND descricaoAuto");
            where = where.replaceAll("sentenca", " C_sentenca <= "+ classeSeguranca.getNum() +" AND sentenca");
            //System.out.println(where);*/

//            retorno = quebra[0] + " WHERE " + where;
        } else {
            where = "TC <= " + classeSeguranca.getNum();
        }

        return where;
    }

    private String montaInsercao(String sql, ClasseSeguranca classeSeguranca) {
        System.out.println(sql);
        sql = sql.replaceAll("\\);", "").replaceAll("\\)", "");
        String[] a = sql.split("\\(");
        System.out.println("------------");
        System.out.println(sql);
        System.out.println(a[0]);
        System.out.println(a[1]);
        String[] quebra = a[1].split(",");
        String retorno = "INSERT INTO processo VALUES(";
        if (classeSeguranca == ClasseSeguranca.NAO_CLASSIFICADA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 0 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 0 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 0 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 0 + ", "; //sentenca
            retorno = retorno + " 0);";
        } else if (classeSeguranca == ClasseSeguranca.CONFIDENCIAL) {
            retorno = retorno + quebra[0] + ", " + 1 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 1 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 1 + ", "; //sentenca
            retorno = retorno + " 1);";
        } else if (classeSeguranca == ClasseSeguranca.SECRETA) {
            retorno = retorno + quebra[0] + ", " + 2 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 2 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 2 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 2 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 2 + ", "; //sentenca
            retorno = retorno + " 3);";
        } else if (classeSeguranca == ClasseSeguranca.ALTAMENTE_SECRETA) {
            retorno = retorno + quebra[0] + ", " + 3 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 3 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 3 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 3 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 3 + ", "; //sentenca
            retorno = retorno + " 3);";
        }
        return retorno;
    }

    private String montaAlteracao(String sql, ClasseSeguranca classeSeguranca) {
        sql = sql.replaceAll("\\);", "");
        String[] a = sql.split("\\(");
        String[] quebra = a[1].split(",");
        String retorno = "UPDATE processo SET";
        if (classeSeguranca == ClasseSeguranca.NAO_CLASSIFICADA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 0 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 0 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 0 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 0 + ", "; //sentenca
            retorno = retorno + " 0);";
        } else if (classeSeguranca == ClasseSeguranca.CONFIDENCIAL) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 1 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 1 + ", "; //sentenca
            retorno = retorno + " 1);";
        } else if (classeSeguranca == ClasseSeguranca.SECRETA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 2 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 2 + ", "; //sentenca
            retorno = retorno + " 2);";
        } else if (classeSeguranca == ClasseSeguranca.ALTAMENTE_SECRETA) {
            retorno = retorno + " AND TC = 3;";
        }
        return retorno;
    }
}
